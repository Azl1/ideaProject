package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Apprentice;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.securety.jwt.JwtTokenProvider;
import com.abdullaevaziz.securety.jwt.JwtUser;
import com.abdullaevaziz.service.ApprenticeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/apprentice")
public class ApprenticeController {

    private ApprenticeService apprenticeService;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public ApprenticeController(ApprenticeService apprenticeService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider){
        this.apprenticeService = apprenticeService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    private ObjectMapper objectMapper = new ObjectMapper();
    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /*@Autowired
    public void setAdminService(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }*/

    /**
     * • post – осуществляет добавление нового ученика в базу данных (доступен всем)
     */
    /*@PostMapping
    public ResponseEntity<ResponseResult<Apprentice>> add(@RequestBody Apprentice apprentice) {
        try {
            this.apprenticeService.add(apprentice);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }*/
    @PostMapping
    public void add(@RequestBody Apprentice apprentice, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Apprentice apprenticeNew = this.apprenticeService.add(apprentice);
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Apprentice>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Apprentice>(null, apprenticeNew));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
        }
    }

    /**
     * • get – осуществляет получение ученика по id (admin, trainer, а apprentice – только себя)
     */
    /*@GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> get(@PathVariable long id, Authentication authentication) {
        try {
            Apprentice apprentice = this.apprenticeService.get(authentication, id);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }*/
    @GetMapping(path = "/{id}")
    public void get(@PathVariable long id, Authentication authentication,  HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        if (authentication != null && authentication.isAuthenticated()) {
            //long id = ((UserDetailsImpl) authentication.getPrincipal()).getId();
            try {
                Apprentice apprenticeGet = apprenticeService.get(authentication, id);
                this.objectMapper.writerFor(new TypeReference<ResponseResult<Apprentice>>() {
                        })
                        .writeValue(response.getOutputStream(),
                                new ResponseResult<Apprentice>(null, apprenticeGet));
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                this.objectMapper.writeValue(response.getOutputStream(),
                        new ResponseResult<Apprentice>(e.getMessage(), null));
            } catch (IllegalAccessException e) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                this.objectMapper.writeValue(response.getOutputStream(),
                        new ResponseResult<Apprentice>(e.getMessage(), null));
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>("Incorrect authentication", null));
        }
    }

    /**
     * • get – осуществляет получение всех учеников (admin, trainer)
     */
    /*@GetMapping
    public ResponseEntity<ResponseResult<List<Apprentice>>> getAll() {
        List<Apprentice> list = this.apprenticeService.getListApprentice();
        return new ResponseEntity<>(new ResponseResult<>(null, list),
                HttpStatus.OK);
    }*/
    @GetMapping
    public void get(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            List<Apprentice> apprenticeList = this.apprenticeService.getListApprentice();
            response.setStatus(HttpStatus.OK.value());
            this.objectMapper.writerFor(new TypeReference<ResponseResult<List<Apprentice>>>() {})
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<List<Apprentice>>(null, apprenticeList));
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
        }
    }

    /**
     * •	put – осуществляет обновление ученика по его id (admin, apprentice – только себя)
     */
    /*@PutMapping
    public ResponseEntity<ResponseResult<Apprentice>> update(@RequestBody Apprentice apprentice, Authentication authentication) {
        try {
            if (apprentice.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Apprentice apprenticeOld = this.apprenticeService.update(authentication, apprentice);
            return new ResponseEntity<>(new ResponseResult<>(null, apprenticeOld),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
        catch (IllegalAccessException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }*/
    @PutMapping
    public void update(@RequestBody Apprentice apprentice, Authentication authentication,
                       HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Apprentice updatedApprentice = this.apprenticeService.update(authentication, apprentice);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Apprentice>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<>(null, updatedApprentice));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
        } catch (IllegalAccessException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
        }
    }

    /**
     * • delete – осуществляет удаление ученика и всех записей, связанных с ним (admin, apprentice – только себя)
     */
    /*@DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> delete(@PathVariable long id, Authentication authentication) {
        try {
            Apprentice apprenticeDelete = this.apprenticeService.delete(authentication, id);
            return new ResponseEntity<>(new ResponseResult<>(null, apprenticeDelete),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.FORBIDDEN);
        }
    }*/
    @DeleteMapping("/{id}")
    public void delete(@PathVariable  long id, Authentication authentication, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            Apprentice apprenticeDelete = this.apprenticeService.delete(authentication,id);
            this.objectMapper.writerFor(new TypeReference<ResponseResult<Apprentice>>() {
                    })
                    .writeValue(response.getOutputStream(),
                            new ResponseResult<Apprentice>(null, apprenticeDelete));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
        } catch (IllegalAccessException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            this.objectMapper.writeValue(response.getOutputStream(),
                    new ResponseResult<Apprentice>(e.getMessage(), null));
        }
    }
}
