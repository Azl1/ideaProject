package com.abdullaevaziz.util;

import java.nio.file.Path;

public class FilePair {
    private Path dir;
    private Path file;

    public FilePair(Path dir, Path file) {
        this.dir = dir;
        this.file = file;
    }

    @Override
    public String toString() {
        return "FilePair{" +
                "dir=" + dir +
                ", file=" + file +
                '}';
    }
}
