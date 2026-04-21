package repository;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SiteRepository {
    private String str;

    public SiteRepository(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        /*try(BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream())){
            byte[] bytes = bufferedInputStream.readAllBytes();
            this.str = new String(bytes);
        }*/

        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
        }
        this.str = stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "SiteRepository{" +
                "str='" + str + '\'' +
                '}';
    }
}
