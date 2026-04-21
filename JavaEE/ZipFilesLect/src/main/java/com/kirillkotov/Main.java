package com.kirillkotov;

import net.lingala.zip4j.ZipFile;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try(ZipFile zipFile = new ZipFile(new File("test.zip"))) {
            zipFile.addFile("4.docx");
            zipFile.addFile("7.txt");
            zipFile.addFile("9.docx");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try(ZipFile zipFile = new ZipFile(new File("test.zip"))) {
            zipFile.extractAll("extract");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}