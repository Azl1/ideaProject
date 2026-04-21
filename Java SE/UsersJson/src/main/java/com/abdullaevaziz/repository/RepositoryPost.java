package com.abdullaevaziz.repository;

import com.abdullaevaziz.example.Post;
import com.abdullaevaziz.example.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class RepositoryPost {
    /**
     * 4. Аналогично создать модель данных и репозиторий для объектов,
     * вернувшихся с указанного ресурса: https://jsonplaceholder.typicode.com/posts.
     * Начальный класс имеет название: Post
     */

    private ObjectMapper objectMapper = new ObjectMapper();

    private ArrayList<Post> postList = new ArrayList<>();

    public RepositoryPost() {
    }

    public RepositoryPost(String urlSite) throws IOException {
        URL url = new URL(urlSite);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try (BufferedInputStream bufferedReader = new BufferedInputStream(httpURLConnection.getInputStream())) {
            this.postList = objectMapper.readValue(bufferedReader, new TypeReference<>() {});
        }
    }

    /**
     * 6.	В PostRepository написать метод, который принимает на вход UserRepository и
     * возвращает HashMap<User, ArrayList<Post>>, показывая, какому пользователю соответствуют какие посты.
     * Для определения автора поста в объекте Post имеется поле userId,
     * соответствующее полю id из класса User.
     * При тестировании данного метода вывести на экран словарь в следующем порядке:
     * userId: все id постов этого user
     */
    //TODO Списал с инета
    public HashMap<User, ArrayList<Post>> mapUserPosts(RepositoryUser repositoryUser) {
        HashMap<User, ArrayList<Post>> userPostsMap = new HashMap<>();
        //TODO цикл по списку постов
        for (Post post : this.postList) {
            //TODO в цикле для поста получить его юзер айди
            int postUserId = post.getUserId();
            //TODO по юзер айди получить юзера
             User user1 = repositoryUser.searchId(postUserId);

             //TODO накопить по ключу юзера в мапу данный пост
            ArrayList<Post> postArrayList = userPostsMap.getOrDefault(user1, new ArrayList<>());
            postArrayList.add(post);
            userPostsMap.put(user1, postArrayList);

        }

        return userPostsMap;
    }

    //TODO сделать ту стринг

    @Override
    public String toString() {
        return "RepositoryPost{" +
                " postList=" + this.postList +
                '}';
    }
}
