package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.UserFile;
import com.abdullaevaziz.securety.jwt.JwtTokenProvider;
import com.abdullaevaziz.service.FileSystemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fileSystem")
public class FileSystemController {

    private FileSystemService fileSystemService;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public void setFileSystemService(FileSystemService fileSystemService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.fileSystemService = fileSystemService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private ObjectMapper objectMapper = new ObjectMapper();
    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @PostMapping("/createPath")
    public ResponseEntity<ResponseResult<String>> createPathNewDir(@PathVariable long id) {
        try {
            this.fileSystemService.createBaseUserDir(id);
            return new ResponseEntity<>(new ResponseResult<>(null, "ОК"),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }


    /**
     * 3. Создание новой директории (посылаешь на вход контроллеру path=1/cat,
     * dir=newdir тогда в папке юзера 1 в папке cat создается новая папка newdir)
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseResult<String>> createPathNewDir(Authentication authentication,
                                                                   @RequestParam String path,
                                                                   @RequestParam String dir) {
        try {
            this.fileSystemService.createDirectory(authentication, path, dir);
            return new ResponseEntity<>(new ResponseResult<>(null, "OK"),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 2. Проверка существования директории
     */
    @GetMapping("/exists")
    public ResponseEntity<ResponseResult<Boolean>> existsDirectory(Authentication authentication,
                                                                   @RequestParam String path) {
        try {
            boolean res = this.fileSystemService.existsDirectory(authentication, path);
            return new ResponseEntity<>(new ResponseResult<>(null, res),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 4. Удаление директории
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseResult<String>> deleteDirectory(Authentication authentication,
                                                                   @RequestParam String path) {
        try {
             this.fileSystemService.deleteDirectory(authentication, path);
            return new ResponseEntity<>(new ResponseResult<>(null, "OK"),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 5. Переименование директории
     */
    @PutMapping("/rename")
    public ResponseEntity<ResponseResult<Boolean>> renameDirectory(Authentication authentication,
                                                                   @RequestParam String path,
                                                                   @RequestParam String newName) {
        try {
            boolean res = this.fileSystemService.renameDirectory(authentication, path, newName);
            return new ResponseEntity<>(new ResponseResult<>(null, res),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
    /*@PutMapping("/rename")
    public void renameDirectory(HttpServletResponse response, Authentication authentication,
                                   @RequestParam String path,
                                   @RequestParam String newName) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            boolean res = this.fileSystemService.renameDirectory(authentication, path, newName);
                this.objectMapper.writerFor(new TypeReference<ResponseResult<User>>() {
                        })
                        .writeValue(response.getOutputStream(),
                                new ResponseResult<Boolean>(null, res));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<User>(e.getMessage(), null));
        }
    }*/


    /**
     * 6. Загрузку файла и отдельно директории на сервер
     */
    @PostMapping(value = "/uploadFile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseResult<Boolean>> uploadFile(Authentication authentication,
                                                              @RequestParam String path,
                                                              @RequestPart MultipartFile document) {
        try {
            boolean res = this.fileSystemService.loadingFileDirectory(authentication, path, document);
            return new ResponseEntity<>(new ResponseResult<>(null, res),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 6. Загрузку файлов и отдельно директории на сервер
     */
    @PostMapping(value = "/uploadFolder", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseResult<Boolean>> uploadFolder(Authentication authentication,
                                                                @RequestParam String path,
                                                                MultipartFile[] document) {
        try {
            boolean res = this.fileSystemService.loadingListFileDirectory(authentication, path, document);
            return new ResponseEntity<>(new ResponseResult<>(null, res),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * 7. Получение файла (файлов) и отдельно директории с сервера
     */
    @GetMapping("/downloadFile")
    public void getFileDirectory(HttpServletResponse response, Authentication authentication,
                                 @RequestParam String filePath) throws IOException {
        try {
            fileSystemService.downloadFile(response, authentication, filePath);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            response.setContentType("application/json;charset=utf-8");
            new ObjectMapper().writeValue(response.getWriter(),
                    new ResponseResult<>(e.getMessage(), null));
        }
    }

    /**
     * 7. Получение директории с сервера
     * директорию вернуть в виде зип архива одним файлом
     */
    @GetMapping(value = "/fileZip")
    public void downloadFileZip(HttpServletResponse response, Authentication authentication,
                                @RequestParam String filePath) throws IOException {
        try {
            this.fileSystemService.downloadFileZip(response, authentication, filePath);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            response.setContentType("application/json;charset=utf-8");
            new ObjectMapper().writeValue(response.getWriter(),
                    new ResponseResult<>(e.getMessage(), null));
        }
    }


    /**
     * 8. Получение информации о файлах и папок пользователя на сервере
     */
    @GetMapping(value = "/informationDirName")
    public ResponseEntity<ResponseResult<List<UserFile>>> getInformationFiles(Authentication authentication,
                                                                        @RequestParam String dirName) {
        try {
            List<UserFile> res = this.fileSystemService.getInformationFiles(authentication, dirName);
            return new ResponseEntity<>(new ResponseResult<>(null, res),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 9. Получение информации о файлах и папок пользователя на клиенте корень директории
     */
    @GetMapping("getListFiles")
    public ResponseEntity<ResponseResult<List<UserFile>>>  getListFiles(Authentication authentication){

        List<UserFile> list = fileSystemService.getListFiles(authentication);
        return new ResponseEntity<>(new ResponseResult<>(null, list),
                HttpStatus.OK);
    }
}
