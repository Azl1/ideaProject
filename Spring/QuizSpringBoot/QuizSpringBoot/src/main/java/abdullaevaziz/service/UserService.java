package abdullaevaziz.service;

import abdullaevaziz.model.User;
import abdullaevaziz.model.UserType;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void addUser(User user, UserType userType);
    void addAdmin(User user, UserType userType);
    User findByUsername(String login);
    User getAuthenticatedUser(Authentication authentication);
    List<User> getListUsers(Authentication authentication);
    User get(Authentication authentication, long id);
}
