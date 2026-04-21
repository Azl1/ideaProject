package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserFile;
import com.abdullaevaziz.model.UserFileType;
import com.abdullaevaziz.model.UserType;
import com.abdullaevaziz.repository.UserRepository;
import com.abdullaevaziz.securety.jwt.JwtUser;
import net.lingala.zip4j.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.FileSystemUtils.deleteRecursively;

@Service
public class FileSystemServiceImpl implements FileSystemService {

    private static final String BASE_PATH = "C:\\users_files";

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 1. Создает корневую папку для пользователя на сервере
     */
    @Override
    public void createBaseUserDir(long userId) {
        File newPath = new File(BASE_PATH, String.valueOf(userId));
        newPath.mkdirs();
    }

    /**
     * 2. Проверка существования директории
     */
    public boolean existsDirectory(Authentication authentication, String path) {
        String[] pathIdUser = path.split("/");
        long t = Long.parseLong(pathIdUser[0]);
        long autUserId = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userService.get(autUserId);
        if (user.getUserType() != UserType.ADMIN && t != autUserId) {
            throw new IllegalArgumentException("Нет доступа!");
        }
        File tempDir = new File(BASE_PATH, path);
        return tempDir.exists();
    }

    /**
     * 3.	Создание новой директории (посылаешь на вход контроллеру path=1/cat,
     * dir=newdir тогда в папке юзера 1 в папке cat создается новая папка newdir)
     */
    @Override
    public void createDirectory(Authentication authentication, String path, String dir) {
        String[] pathIdUser = path.split("/");
        long t = Long.parseLong(pathIdUser[0]);
        long autUserId = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userService.get(autUserId);
        if (user.getUserType() != UserType.ADMIN && t != autUserId) {
            throw new IllegalArgumentException("Нет доступа!");
        }

        File where = new File(BASE_PATH, path);
        if (!where.isDirectory()) {
            throw new IllegalArgumentException("Такая директория не существует!");
        }

        File file = new File(where, dir);
        if (file.exists()) {
            throw new IllegalArgumentException("Такая папка уже существует!");
        }
        file.mkdirs();
    }

    /**
     * 4. Удаление директории
     */
    @Override
    public boolean deleteDirectory(Authentication authentication, String path) {
        File directory = new File(BASE_PATH, path);
        if (!existsDirectory(authentication, path)) {
            throw new IllegalArgumentException("Такая папка не существует!");
        }
        deleteRecursively(directory);
        return true;
    }

    /**
     * 5. Переименование директории
     */
    @Override
    public boolean renameDirectory(Authentication authentication, String oldPath, String newName) {

        if (!existsDirectory(authentication, oldPath)) {
            throw new IllegalArgumentException("Такая папка не существует!");
        }
        File fileOld = new File(BASE_PATH, oldPath);
        File parentDir = fileOld.getParentFile();
        File fileNew = new File(parentDir, newName);
        return fileOld.renameTo(fileNew);
    }


    /**
     * 6. Загрузку файла на сервер
     */
    @Override
    public boolean loadingFileDirectory(Authentication authentication, String path, MultipartFile document) {
        try {
            if (!existsDirectory(authentication, path)) {
                throw new IllegalArgumentException("Такая папка не существует!");
            }
            String originalFilename = document.getOriginalFilename();

            File dir = new File(BASE_PATH, path);
            byte[] bytes = document.getBytes();
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(new File(dir, originalFilename)))) {
                bufferedOutputStream.write(bytes);
                return true;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка сохранения файла!");
        }
    }

    /**
     * 6. Загрузку файлов и отдельно директории на сервер
     */
    @Override
    public boolean loadingListFileDirectory(Authentication authentication, String path, MultipartFile[] document) {
        try {
            if (!existsDirectory(authentication, path)) {
                throw new IllegalArgumentException("Такая папка не существует!");
            }
            File dir = new File(BASE_PATH, path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (MultipartFile multipartFile : document) {
                String originalFilename = multipartFile.getOriginalFilename();
                byte[] bytes = multipartFile.getBytes();
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                        new FileOutputStream(new File(dir, originalFilename)))) {
                    bufferedOutputStream.write(bytes);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Ошибка сохранения файлов!");
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    /**
     * 7. Получение файла с сервера
     */
    /*@Override
    public File downloadFile(HttpServletResponse response, Authentication authentication, String path) {

        if (!existsDirectory(authentication, path)) {
            throw new IllegalArgumentException("Такая папка не существует!");
        }

        File file = new File(BASE_PATH, path);

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(file))) {
            response.getOutputStream().write(bufferedInputStream.readAllBytes());
            String mime = Files.probeContentType(file.toPath());
            response.setContentType(mime);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error file uploading");
        }
        return file;
    }*/

    /**
     * 7. Получение ФАЙЛА с сервера
     */
    @Override
    public void downloadFile(HttpServletResponse response, Authentication authentication, String filePath) {
        File file = new File(BASE_PATH, filePath);
        if (!file.exists()){
            throw new IllegalArgumentException("Файл не существует: " + filePath);
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Это папка, используйте метод downloadFolder");
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            String mime = Files.probeContentType(file.toPath());
            response.setContentType(mime != null ? mime : "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.getOutputStream().write(bufferedInputStream.readAllBytes());
        } catch (IOException e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Error file uploading");
    }
    }

    /**
     * 7. Получение директории с сервера
     * директорию вернуть в виде зип архива одним файлом
     */
    @Override
    public void downloadFileZip(HttpServletResponse response, Authentication authentication, String filePath) {
        long userIdGet = ((JwtUser) authentication.getPrincipal()).getId();
        String zipName = "user_" + userIdGet + "_files.zip";
        File tempZip = new File(zipName);
       /* String folderName = new File(filePath).getName();
        String zipName = folderName + ".zip";*/
        try (ZipFile zipFile = new ZipFile(new File(String.valueOf(tempZip)))) {
            if (!existsDirectory(authentication, filePath)) {
                throw new IllegalArgumentException("Такая папка не существует!");
            }
            File file = new File(BASE_PATH, filePath);
            zipFile.addFolder(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File zipFile = new File(zipName);
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(zipFile))) {
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + zipName + "\"");
            response.getOutputStream().write(stream.readAllBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("Error file uploading:==" + e.getMessage());
        } finally {
            if (zipFile.exists()) {
                zipFile.delete();
            }
        }
    }


    /**
     * 8. Получение информации о файлах и папок пользователя на сервере
     */
    @Override
    public List<UserFile> getInformationFiles(Authentication authentication, String dirName) {
        if (!existsDirectory(authentication, dirName)) {
            throw new IllegalArgumentException("Такая папка не существует!");
        }
        List<UserFile> userFileList = new ArrayList<>();
        //dirName = dirName.replace("/", File.separator);
        File directory = new File(BASE_PATH, dirName);
        File[] allFiles = directory.listFiles();
        UserFile userFile;
        for (File f : allFiles) {
            UserFileType fileType;
            if (f.isDirectory()) {
                fileType = UserFileType.DIR;
            } else {
                fileType = UserFileType.FILE;
            }
            userFile = new UserFile(f.getPath().replace("C:\\users_files\\", ""), fileType);
            userFileList.add(userFile);
        }
        return userFileList;
    }

    /**
     * 9. Получение информации о файлах и папок пользователя на клиенте корень директории
     */
    @Override
    public List<UserFile> getListFiles(Authentication authentication) {
        long autUserId = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userService.get(autUserId);

        List<UserFile> userFileList = new ArrayList<>();
        File directory = new File("C:\\");
        File[] allFiles = directory.listFiles();
        UserFile userFile;
        for (File f : allFiles) {
            UserFileType fileType;
            if (f.isDirectory()) {
                fileType = UserFileType.DIR;
            } else {
                fileType = UserFileType.FILE;
            }
            userFile = new UserFile(f.getPath().replace("C:\\users_files\\", ""), fileType);
            userFileList.add(userFile);
        }
        return userFileList;
    }



}
