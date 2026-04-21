package com.abdullaevaziz.main;

import com.abdullaevaziz.example.Post;
import com.abdullaevaziz.example.User;
import com.abdullaevaziz.repository.RepositoryPost;
import com.abdullaevaziz.repository.RepositoryUser;
import com.abdullaevaziz.util.Json2PojoGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        /*Json2PojoGenerator generator1 = new Json2PojoGenerator("user.json",
                "src/main/java/");
        generator1.generate("User", "com.abdullaevaziz.example");

        Json2PojoGenerator generator2 = new Json2PojoGenerator("post.json",
                "src/main/java/");
        generator2.generate("Post", "com.abdullaevaziz.example");*/

        RepositoryUser repositoryUser = new RepositoryUser("https://jsonplaceholder.typicode.com/users");
        RepositoryPost repositoryPost = new RepositoryPost("https://jsonplaceholder.typicode.com/posts");

        System.out.println(repositoryUser);
        System.out.println(repositoryPost);

        System.out.println("1-------------------------------");
        User user1 = repositoryUser.searchId(5);
        User user2 = repositoryUser.searchId(7);
        System.out.println(user1);
        System.out.println(user2);

        /**
         * При тестировании данного метода вывести на экран словарь в следующем порядке:
         * userId: все id постов этого user
         */
        System.out.println("2-------------------------------");
        HashMap<User, ArrayList<Post>> userPostsMap = repositoryPost.mapUserPosts(repositoryUser);
        for (User user : userPostsMap.keySet()) {
            System.out.print("UserId: ");
            for (Post post : userPostsMap.get(user)) {
                System.out.print(post.getId() + " ");
            }
            System.out.println();
        }

    }
}
