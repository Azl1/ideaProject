package com.abdullaevaziz.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService{


    @Override
    public File getPhoto(String fileName) {
        File file = new File("Photos");
        File[] getFileMass = file.listFiles();
        for (File fileMass : getFileMass) {
            String getNameFile = fileMass.getName();
            if (fileName.equals(getNameFile)){
                if (fileMass.isFile()) {
                    return fileMass;
                }
            }
        }
        return null;
    }

    @Override
    public String getAllPhotos() {
        File file = new File("Photos");
        File[] getFile = file.listFiles();
        String getFileName =
                Arrays.stream(getFile).map(File::getName).collect(Collectors.joining("\n"));
        return getFileName;
    }
}
