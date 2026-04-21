package com.abdullaevaziz.service;

import com.abdullaevaziz.model.UserFile;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserFileService {

    UserFile saveFile(Authentication authentication, MultipartFile document);

    List<UserFile> getUserFiles(Authentication authentication);

    void download(Authentication authentication, String filename, HttpServletResponse response);
}
