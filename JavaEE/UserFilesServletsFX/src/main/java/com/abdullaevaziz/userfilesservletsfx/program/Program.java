package com.abdullaevaziz.userfilesservletsfx.program;

import com.abdullaevaziz.userfilesservletsfx.client.HttpMultipart;
import com.abdullaevaziz.userfilesservletsfx.constants.Constants;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Program {

    public static void main(String[] args) {
        try {
            Map<String, String> headers = new HashMap<>();
            HttpMultipart multipart = new HttpMultipart(
                    Constants.SERVER_URL + "/file_upload", "utf-8", headers);
            multipart.addFormField("note", "test_name");
            multipart.addFormField("password", "test_psw");
            multipart.addFilePart("file", new File("photo.png"));
            String response = multipart.finish();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String fileName = "photo.png";
            String s = Constants.SERVER_URL + "/file_upload_servlet?filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            File downloaded = new File("C:\\dowloaded");
            downloaded.mkdirs();
            HttpMultipart.getMultiPart(s, downloaded + File.separator + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
