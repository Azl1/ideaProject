package com.kirillkotov.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {

    void download(HttpServletResponse response, String filename);

    void save(String arg, MultipartFile document) throws IOException;
}
