package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Admin;
import com.abdullaevaziz.model.Apprentice;
import com.abdullaevaziz.service.AdminService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private ObjectMapper objectMapper = new ObjectMapper();
    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * • post – осуществляет прием данных и
     * производит регистрацию нового администратора школы
     * в системе (admin)
     */
    /*@PostMapping
    public ResponseEntity<ResponseResult<Admin>> add(@RequestBody Admin admin) {
        try {
            this.adminService.add(admin);
            return new ResponseEntity<>(new ResponseResult<>(null, admin),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping
    public void add(@RequestBody Admin admin, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Admin adminNew = this.adminService.add(admin);
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Admin>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Admin>(null, adminNew));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Admin>(e.getMessage(), null));
        }
    }

    /**
     * • get – осуществляет получение
     * администратора по его id (admin)
     */
    /*@GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Admin>> get(@PathVariable long id) {
        try {
            Admin admin = this.adminService.get(id);
            return new ResponseEntity<>(new ResponseResult<Admin>(null, admin),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/
    @GetMapping(path = "/{id}")
    public void get(@PathVariable long id, Authentication authentication,  HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        if (authentication != null && authentication.isAuthenticated()) {
            //long id = ((UserDetailsImpl) authentication.getPrincipal()).getId();
            try {
                Admin adminGet = adminService.get(authentication, id);
                this.objectMapper.writerFor(new TypeReference<ResponseResult<Admin>>() {
                        })
                        .writeValue(response.getOutputStream(),
                                new ResponseResult<Admin>(null, adminGet));
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                this.objectMapper.writeValue(response.getOutputStream(),
                        new ResponseResult<Admin>(e.getMessage(), null));
            } catch (IllegalAccessException e) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                this.objectMapper.writeValue(response.getOutputStream(),
                        new ResponseResult<Admin>(e.getMessage(), null));
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Admin>("Incorrect authentication", null));
        }
    }

    /**
     * • get – осуществляет получение всех
     * администраторов (admin)
     */
   /* @GetMapping
    public ResponseEntity<ResponseResult<List<Admin>>> getAll() {
        List<Admin> list = this.adminService.getListAdmin();
        return new ResponseEntity<>(new ResponseResult<>(null, list),
                HttpStatus.OK);
    }*/
    @GetMapping
    public void get(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Admin> adminList = this.adminService.getListAdmin();
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Admin>>>() {})
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Admin>>(null, adminList));
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Admin>(e.getMessage(), null));
        }
    }

    /**
     * • put – осуществляет обновление администратора по его id (admin)
     */
    /*@PutMapping
    public ResponseEntity<ResponseResult<Admin>> update(@RequestBody Admin admin) {
        try {
            if (admin.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Admin adminOld = this.adminService.update(admin);
            return new ResponseEntity<>(new ResponseResult<>(null, adminOld),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/
    @PutMapping
    public void update(@RequestBody Admin admin, Authentication authentication,
                       HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Admin updatedAdmin = this.adminService.update(authentication, admin);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Admin>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<>(null, updatedAdmin));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Admin>(e.getMessage(), null));
        } catch (IllegalAccessException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Admin>(e.getMessage(), null));
        }
    }

    /**
     * • delete – осуществляет удаление администратора (admin)
     */
    /*@DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<Admin>> delete(@PathVariable long id){
        try {
            Admin adminDelete = this.adminService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, adminDelete),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/
    @DeleteMapping("/{id}")
    public void delete(@PathVariable  long id, Authentication authentication, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Admin adminDelete = this.adminService.delete(authentication,id);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Admin>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Admin>(null, adminDelete));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Admin>(e.getMessage(), null));
        } catch (IllegalAccessException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Admin>(e.getMessage(), null));
        }
    }
}
