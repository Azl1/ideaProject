package com.kirillkotov.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /**
         * Files
         */
        File fileDir1 = new File("C:\\test_dir\\123");
        fileDir1.mkdirs();
        System.out.println("Result of check directory");
        System.out.println(fileDir1.isDirectory());

        System.out.println("\nResult of get parent file");
        File parentFileDir1 = fileDir1.getParentFile();
        System.out.println(parentFileDir1);

        File fileDir2 = new File(fileDir1, "1234");
        fileDir2.mkdirs();

        File file1 = new File(fileDir1, "123.txt");
        try {
            file1.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nResult of check file");
        System.out.println(file1.isFile());

        System.out.println("\nResult of get name");
        System.out.println(file1.getName());

        File fileDir3 = new File("test_dir");
        System.out.println("\nResult of check exists");
        System.out.println(fileDir3.exists());

        System.out.println("\nResult of print file object");
        System.out.println(fileDir3);

        System.out.println("\nResult of get absolute path string");
        System.out.println(fileDir3.getAbsolutePath());

        System.out.println("\nResult of get absolute path file");
        File absoluteFileDir3 = fileDir3.getAbsoluteFile();
        System.out.println(absoluteFileDir3);

        boolean deleteFileDir1 = fileDir1.delete();
        System.out.println("\nResult of delete not empty directory");
        System.out.println(deleteFileDir1);

        File fileDir4 = new File("C:\\1");
        fileDir4.mkdirs();

        System.out.println("\nResult of delete empty directory");
        boolean deleteFileDir4 = fileDir4.delete();
        System.out.println(deleteFileDir4);

        boolean equals = fileDir1.equals(fileDir2);
        System.out.println("\nResult of equals file objects");
        System.out.println(equals);

        System.out.println("\nResult of get name");
        String name = fileDir1.getName();
        System.out.println(name);


        File[] files = fileDir1.listFiles();
        if(files != null){
            System.out.println("\nResult of print all files and directories in directory");
            for (File file : files) {
                System.out.println(file);
            }
        }

        File fileDir5 = new File(fileDir3, "12345\\1");
        fileDir5.mkdirs();
        boolean result = fileDir5.renameTo(new File(fileDir3, "1"));
        System.out.println("\nResult of renate to(rename, move)");
        System.out.println(result);

        /**
         * Paths
         */
        Path path = fileDir4.toPath();

        Path pathDir1 = Path.of("C:\\test_dir\\123");
        System.out.println("\nResult of get path from file");
        System.out.println(pathDir1);

        Path name1 = pathDir1.getName(1);
        System.out.println("\nResult of get name by index");
        System.out.println(name1);

        boolean result1 = pathDir1.endsWith(Path.of("1"));
        boolean result2 = pathDir1.startsWith(Path.of("test_dir"));
        System.out.println("\nResult of check ends with path");
        System.out.println(result1);

        System.out.println("\nResult of check starts with path");
        System.out.println(result2);

        int nameCount = pathDir1.getNameCount();
        System.out.println("\nResult of get name count from path");
        System.out.println(nameCount);

        Path parent = pathDir1.getParent();
        System.out.println("\nResult of get parent path");
        System.out.println(parent);

        Path root = pathDir1.getRoot();
        System.out.println("\nResult of get root path");
        System.out.println(root);

        Path subpath = pathDir1.subpath(0, 1);
        System.out.println("\nResult of subpath from path");
        System.out.println(subpath);

        Path absolutePath = Path.of("test_dir").toAbsolutePath();
        System.out.println("\nResult of absolute path");
        System.out.println(absolutePath);

        File file = pathDir1.toFile();
        System.out.println("\nResult of get file from path");
        System.out.println(file);

        Path pathFile = Path.of("test_dir/palych.txt");
        try {
            List<String> strings = Files.lines(pathFile).filter(x -> x.length() == 4).toList();
            System.out.println("\nResult of get stream from path");
            System.out.println(strings);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try(BufferedReader bufferedReader = Files.newBufferedReader(pathFile)){
            //BufferedWriter
            //InputStream
            //OutputStream
            String s = bufferedReader.readLine();
            System.out.println("\nResult of open buffered reader from path and read line");
            System.out.println(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            byte[] bytes = Files.readAllBytes(pathFile);
            System.out.println("\nResult of read all bytes from path");
            System.out.println(Arrays.toString(bytes));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<String> strings = Files.readAllLines(pathFile);
            System.out.println("\nResult of read all lines from path");
            System.out.println(strings);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            String s = Files.readString(pathFile);
            System.out.println("\nResult of read string from path");
            System.out.println(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<String> arr = List.of("ds", "dsd");
        try {
            Files.write(new File(fileDir1, "newFile1.txt").toPath(), arr);
            System.out.println("\nCreating new file newFile1.txt successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            Files.writeString(new File(fileDir1, "newFile2.txt").toPath(), "Hello World");
            System.out.println("\nCreating new file newFile2.txt successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Path pathDir2 = Path.of("C:\\test_dir");
        try {
            System.out.println("\nResult of walk recursive directory");
            Files.walkFileTree(pathDir2, new SimpleFileVisitor<>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file);
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println(dir);
                    return super.preVisitDirectory(dir, attrs);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Using StreamAPI for walk by file system
         */
        /*try {
            Files.walk(pathDir2)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/
    }
}