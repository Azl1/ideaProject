package com.kiriilkotov;

import com.kiriilkotov.retrofit.FileUploadRepository;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileUploadRepository repository = new FileUploadRepository();
        try {
            //repository.uploadFile("Hello", new File("C:\\Users\\Denis\\Desktop\\Java\\Java RoadMap\\Java Enterprise.docx"));
            repository.downloadFile("7.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}