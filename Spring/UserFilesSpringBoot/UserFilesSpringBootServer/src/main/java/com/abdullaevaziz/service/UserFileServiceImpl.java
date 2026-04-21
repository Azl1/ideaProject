package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserFile;
import com.abdullaevaziz.repository.UserFileRepository;
import com.abdullaevaziz.security.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
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
     * который принимает id пользователя и файл, добавляет информацию
     * об этом файле в БД и сохраняет файл на сервере
     * Таблица UserFiles состоит из колонок: id, filename, serverFilename, user_id.
     * Для одного пользователя файлы повторяться не могут. Так как у разных
     * пользователей названия файлов могут быть одинаковые, то нужно на
     * сервер их сохранять по следующему механизму:
     * 1. Принять файл на сервер
     * 2. Добавить информацию в БД об этом файле, кроме колонки serverFilename
     * 3. После того, как база присвоит id, serverFilename должен быть равен "id.расширение, которое было у файла изначально"
     * 4. Произвести обновление колонки serverFilename, исходя из пункта 3
     * 5. Произвести сохранение файла под именем serverFilename
     */
    @Override
    public UserFile saveFile(Authentication authentication, MultipartFile document) {
        try {
            long id = ((JwtUser) authentication.getPrincipal()).getId();
            User user = this.userService.get(id);

            String originalFilename = document.getOriginalFilename();

            UserFile userFile = new UserFile(originalFilename, user);
            userFile = userFileRepository.save(userFile);

            String extension = "";
            int idIndex = originalFilename.lastIndexOf(".");

            if (idIndex > 0) {
                extension = originalFilename.substring(idIndex);
            }
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

    /**
     * • Написать в FileServlet метод GET,
     * который для заданного id пользователя
     * выдает список его загруженных на сервер файлов.
     */
    @Override
    public List<UserFile> getUserFiles(Authentication authentication) {
        long userIdGet = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userService.get(userIdGet);
        return userFileRepository.findAllByUserId(user.getId());
    }

    /**
     * • Написать в FileService метод GET,
     * который возвращает файл для заданного id пользователя
     * и оригинального имени файла
     */
    @Override
    public void download(
            Authentication authentication,
            String filename, HttpServletResponse response) {
        long userIdGet = ((JwtUser) authentication.getPrincipal()).getId();

        UserFile userFile = userFileRepository.findByUserIdAndFilename(userIdGet, filename)
                .orElseThrow(() -> new IllegalArgumentException("File not found"));

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
