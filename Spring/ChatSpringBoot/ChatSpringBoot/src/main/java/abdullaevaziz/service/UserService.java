package abdullaevaziz.service;


import abdullaevaziz.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getLogin(String login, String password);
    User get(long id);
    List<User> getListUsers();

}
