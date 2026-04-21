package com.abdullaevaziz.service;

import com.abdullaevaziz.model.UserFile;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserFileService {

    UserFile saveFile(Authentication authentication, MultipartFile document);

    List<UserFile> getUserFilesList(Authentication authentication);

    void downloadZip(Authentication authentication, String filename, HttpServletResponse response);

    void downloadFileAndVersion(Authentication authentication, String fileName, Integer version, HttpServletResponse response);

    List<UserFile> getUserFileVersionList (Authentication authentication, String filename);
    void downloadZipList(Authentication authentication, HttpServletResponse response);
}
