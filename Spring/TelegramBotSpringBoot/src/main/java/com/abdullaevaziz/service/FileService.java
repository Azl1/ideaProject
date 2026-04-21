package com.abdullaevaziz.service;

import java.io.File;

public interface FileService {

    File getPhoto(String fileName);
    String getAllPhotos();
}
