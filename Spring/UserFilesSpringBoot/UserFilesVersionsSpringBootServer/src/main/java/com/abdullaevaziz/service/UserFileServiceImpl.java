package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserFile;
import com.abdullaevaziz.repository.UserFileRepository;
import com.abdullaevaziz.security.jwt.JwtUser;
import com.abdullaevaziz.util.Util;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.List;


@Service
@Slf4j
public class UserFileServiceImpl implements UserFileService {

    private UserFileRepository userFileRepository;

    private UserService userService;

    @Autowired
    public void setUserFileRepository(UserFileRepository userFileRepository) {
        this.userFileRepository = userFileRepository;
    }

    @Autowired
    public void setUserFileService(UserService userService) {
        this.userService = userService;
    }

    /**
     * • При добавлении нового файла, если ранее файлов с таким именем не было,
     * версию определить как 1. Если файлы с таким именем ранее уже отправлялись,
     * то проверить MD5 хэш-сумму отправляемого файла и ранее отправленных файлов.
     * Добавить новый файл под новой версией, если хэш-суммы не совпадают,
     * то есть файл с таким содержимым ранее не отправлялся
     */
    @Override
    public UserFile saveFile(Authentication authentication, MultipartFile document) {
        try {
            long id = ((JwtUser) authentication.getPrincipal()).getId();
            User user = this.userService.get(id);

            String originalFilename = document.getOriginalFilename();
            List<UserFile> userFileList = this.userFileRepository.findByUserIdAndFilename(id, originalFilename);
            int versionMax = userFileList.stream().mapToInt(UserFile::getVersion).max().orElse(0) + 1;

            String newFileHash = Util.getMD5Hash(document);

            for (UserFile oldUserFile : userFileList) {
                File oldFile = new File("C:\\files", oldUserFile.getServerFilename());
                if (oldFile.exists()) {
                    String oldHash = Util.getMD5Hash(oldFile.getAbsolutePath());
                    if (newFileHash.equals(oldHash)) {
                        throw new IllegalArgumentException("Такой файл уже существует, версия не создаётся");
                    }
                }
            }

            UserFile userFile = new UserFile(originalFilename, user, versionMax);

            String extension = "";
            int idIndex = originalFilename.lastIndexOf(".");

            if (idIndex > 0) {
                extension = originalFilename.substring(idIndex);
            }

            userFileRepository.save(userFile);
            String serverFilename = userFile.getId() + extension;
            userFile.setServerFilename(serverFilename);

            userFileRepository.save(userFile);

            File fileSave = new File("C:\\files");
            fileSave.mkdirs();

            byte[] bytes = document.getBytes();
            try (BufferedOutputStream bufferedOutputStream
                         = new BufferedOutputStream(new FileOutputStream(new File(fileSave, serverFilename)))) {
                bufferedOutputStream.write(bytes);
            }
            return userFile;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Файл с таким именем уже существует у этого пользователя");
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка сохранения файла!");
        }
    }

    public List<UserFile> getUserFilesList(Authentication authentication) {
        long id = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userService.get(id);
        List<UserFile> list = this.userFileRepository.findAllByUserId(user.getId());
        return list;
    }

    @Override
    public List<UserFile> getUserFileVersionList(Authentication authentication, String filename) {
        long id = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userService.get(id);
        return this.userFileRepository.findAllByUserIdAndFilename(user.getId(), filename);
    }

    /**
     * • Реализовать метод получения файла аутентифицированного пользователя по его имени и версии,
     * вернув zip-архив с оригинальными именами файлов с припиской версии через нижнее подчеркивание
     */
    @Override
    public void downloadZip(
            Authentication authentication,
            String filename, HttpServletResponse response) {
        long userIdGet = ((JwtUser) authentication.getPrincipal()).getId();

        List<UserFile> userFileList = userFileRepository.findAllByUserIdAndFilename(userIdGet, filename);
        if (userFileList.isEmpty()) {
            throw new IllegalArgumentException("У пользователя нет файлов для архивации!");
        }

        String zipName = filename;
        /*if (!zipName.toLowerCase().endsWith(".zip")) {
            zipName = filename + ".zip";
        }*/
        File tempZip = new File(zipName);

        try (ZipFile zipFile = new ZipFile(new File(String.valueOf(tempZip)))) {
            for (UserFile userFile : userFileList) {
                File file = new File("C:\\files", userFile.getServerFilename());
                if (!file.exists()) {
                    System.err.println("Файл не найден: " + file.getAbsolutePath());
                    continue;
                }

                String originalName = userFile.getFilename();
                String extension = "";
                int index = originalName.lastIndexOf(".");
                if (index > 0) {
                    extension = originalName.substring(index);
                    originalName = originalName.substring(0, index);
                }
                String fileInZip = originalName + "_v" + userFile.getVersion() + extension;

                zipFile.addFile(file, new net.lingala.zip4j.model.ZipParameters() {{
                    setFileNameInZip(fileInZip);
                }});
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка при создании ZIP-архива: " + e.getMessage());
        }

        //File zipFile = new File(tempZip);
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(tempZip))) {
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + tempZip + "\"");
            response.getOutputStream().write(stream.readAllBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("Error file uploading:==" + e.getMessage());
        } finally {

            if (tempZip.exists()) {
                tempZip.delete();
            }
        }

    }

    /**
     * • Метод получения всех файлов аутентифицированного пользователя
     * в виде zip-архива с оригинальными именами файлов с припиской версии
     */
    @Override
    public void downloadZipList(
            Authentication authentication, HttpServletResponse response) {
        long userIdGet = ((JwtUser) authentication.getPrincipal()).getId();


        List<UserFile> userFileList = userFileRepository.findAllByUserId(userIdGet);
        if (userFileList.isEmpty()) {
            throw new IllegalArgumentException("У пользователя нет файлов для архивации!");
        }

        String zipName = "user_" + userIdGet + "_files.zip";
        File tempZip = new File(zipName);

        try (ZipFile zipFile = new ZipFile(new File(String.valueOf(tempZip)))) {
            for (UserFile userFile : userFileList) {
                File file = new File("C:\\files", userFile.getServerFilename());
                if (!file.exists()) {
                    System.err.println("Файл не найден: " + file.getAbsolutePath());
                    continue;
                }

                String originalName = userFile.getFilename();
                String extension = "";
                int index = originalName.lastIndexOf(".");
                if (index > 0) {
                    extension = originalName.substring(index);
                    originalName = originalName.substring(0, index);
                }
                String fileInZip = originalName + "_v" + userFile.getVersion() + extension;

                zipFile.addFile(file, new net.lingala.zip4j.model.ZipParameters() {{
                    setFileNameInZip(fileInZip);
                }});
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка при создании ZIP-архива: " + e.getMessage());
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


    @Override
    public void downloadFileAndVersion(Authentication authentication, String fileName, Integer version, HttpServletResponse response) {
        long userIdGet = ((JwtUser) authentication.getPrincipal()).getId();

        UserFile userFile = userFileRepository.findFirstByUserIdAndFilenameAndVersion(userIdGet, fileName, version).
                orElseThrow(() -> new IllegalArgumentException("File not found"));

        File file = new File("C:\\files", userFile.getServerFilename());
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден на сервере!");
        }

        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file))) {
            response.getOutputStream().write(stream.readAllBytes());
            String mime = Files.probeContentType(file.toPath());
            response.setContentType(mime);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error file uploading");
        }
    }


}
