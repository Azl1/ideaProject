package com.kirillkotov.service;

import com.kirillkotov.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${users.url}")
    private String url;

    @Override
    public List<User> get() {
        //TODO for ResponseEntity
        /*ResponseEntity<List> response
                = restTemplate.getForEntity(this.url, List.class);

        List<User> users = response.getBody();
        return users;*/

        //TODO for object
        /*List<?> users
                = this.restTemplate.getForObject(this.url, List.class);

        return (List<User>) users;*/

        //TODO exchange for generic types
        ResponseEntity<List<User>> response = this.restTemplate.exchange(this.url,
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    @Override
    public User add(User user) {
        //TODO for Entity
        /*HttpEntity<User> request = new HttpEntity<>(user);

        User res = restTemplate
                .postForObject(this.url, request, User.class);
        return res;*/

        //TODO for object
        //return this.restTemplate.postForObject(this.url, user, User.class);

        //TODO exchange for generic types
        ResponseEntity<User> response = this.restTemplate.exchange(this.url,
                HttpMethod.POST, new HttpEntity<>(user), new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public User get(long id) {
        return this.restTemplate.exchange(this.url + "/{id}", HttpMethod.GET, null,
                new ParameterizedTypeReference<User>() {}, id).getBody();
    }

    @Override
    public List<User> get(String name) {
        return this.restTemplate.exchange(this.url + "?name={name}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {}, name).getBody();
    }
}
