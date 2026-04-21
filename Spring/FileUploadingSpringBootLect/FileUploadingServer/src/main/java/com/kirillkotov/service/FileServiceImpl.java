package com.kirillkotov.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void download(HttpServletResponse response, String filename) {
        File file = new File("C:\\files", filename);
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file))) {
            response.getOutputStream().write(stream.readAllBytes());
            String mime = Files.probeContentType(file.toPath());
            response.setContentType(mime);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error file uploading");
        }
    }

    @Override
    public void save(String arg, MultipartFile document) throws IOException {
        File fileRoot = new File("C:\\files");
        fileRoot.mkdirs();
        System.out.println(arg);
        String name = document.getOriginalFilename();
        System.out.println(name);
        byte[] bytes = document.getByte();
        try (BufferedOutputStream bufferedOutputStream
                     = new BufferedOutputStream(new FileOutputStream(new File(fileRoot, name)))) {
            bufferedOutputStream.write(bytes);
        }
    }
}