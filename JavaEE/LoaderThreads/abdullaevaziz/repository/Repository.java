package abdullaevaziz.repository;


import abdullaevaziz.model.ProgressChecker;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Repository {

    private URL url;
    private ProgressChecker progressChecker;

    public Repository(String urlSite, ProgressChecker progressChecker) throws MalformedURLException {
        this.url = new URL(urlSite);
        this.progressChecker = progressChecker;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public ProgressChecker getProgressChecker() {
        return progressChecker;
    }

    public void setProgressChecker(ProgressChecker progressChecker) {
        this.progressChecker = progressChecker;
    }

    public void load(String file) throws IOException {
        HttpURLConnection httpConnection = (HttpURLConnection) (this.url.openConnection());
        //Total size of file
        long completeFileSize = httpConnection.getContentLength();
        try (BufferedInputStream in = new BufferedInputStream(httpConnection.getInputStream());
             BufferedOutputStream bout = new BufferedOutputStream(
                     new FileOutputStream(file), 1024)) {
            byte[] data = new byte[1024];
            //Current size of read file
            long downloadedFileSize = 0;
            int x;
            //Read while end of stream
            //Read every block for 1024 bytes
            while ((x = in.read(data, 0, 1024)) >= 0) {
                downloadedFileSize += x;
                bout.write(data, 0, x);
                this.progressChecker.check(downloadedFileSize, completeFileSize);
            }
        }
        finally {
            httpConnection.disconnect();
        }
    }

    @Override
    public String toString() {
        return "Repository{" +
                "url=" + url +
                '}';
    }
}
