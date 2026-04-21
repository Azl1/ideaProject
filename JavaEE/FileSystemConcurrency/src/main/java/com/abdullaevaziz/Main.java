package com.abdullaevaziz;

import com.util.FolderWatcher;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Input args");
            return;
        }
        String argsVal = args[0];
        FolderWatcher folderWatcher = new FolderWatcher(argsVal);
        folderWatcher.run();
    }
}