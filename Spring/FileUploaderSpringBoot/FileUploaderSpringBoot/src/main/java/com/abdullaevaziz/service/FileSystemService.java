package com.abdullaevaziz.service;

import com.abdullaevaziz.model.UserFile;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface FileSystemService {

    void createBaseUserDir(long userId);
    boolean existsDirectory(Authentication authentication, String path);
    void createDirectory (Authentication authentication, String path, String dir);
    boolean deleteDirectory(Authentication authentication, String path);
    boolean  renameDirectory(Authentication authentication, String oldPath, String newName);
    boolean loadingFileDirectory(Authentication authentication, String targetPath, MultipartFile document);
    boolean loadingListFileDirectory(Authentication authentication, String path, MultipartFile[] document);
    void downloadFile(HttpServletResponse response, Authentication authentication, String path);
    void downloadFileZip(HttpServletResponse response, Authentication authentication, String filePath);
    List<UserFile> getInformationFiles (Authentication authentication, String filName);
    List<UserFile> getListFiles(Authentication authentication);

}
