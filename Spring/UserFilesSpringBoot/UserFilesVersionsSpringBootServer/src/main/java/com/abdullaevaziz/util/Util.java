package com.abdullaevaziz.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {

    public static String getMD5Hash(MultipartFile multipartFile) throws IOException {
        try (InputStream is = multipartFile.getInputStream()) {
            return DigestUtils.md5Hex(is);
        }
    }

    public static String getMD5Hash(String filePath) throws IOException {
        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {
            return DigestUtils.md5Hex(is);
        }
    }

}
