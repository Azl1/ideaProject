package abdullaevaziz.service;

import abdullaevaziz.model.User;
import abdullaevaziz.model.UserType;
import abdullaevaziz.repository.UserRepository;
import abdullaevaziz.securety.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 1. Регистрация нового пользователя(после успешной регистрации создается
     * в папке юзерфайлс папка с айдишником созданного юзера)
     */
    @Override
    public void addUser(User user, UserType userType) {
        add(user, userType, "User has already added!");
    }

    @Override
    public void addAdmin(User user, UserType userType) {
        add(user, userType, "Admin has already added!");
    }

    private void add(User user, UserType userType, String s) {
        try {
            user.setUserType(userType);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(s);
        }
    }

    @Override
    public User getAuthenticatedUser(Authentication authentication) {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + login));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new UsernameNotFoundException("Incorrect password user");
        }
        return user;
    }

    @Override
    public User findByUsername(String login) {
        User result = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        log.info("IN findByUsername - admin: {} found by username: {}", result, login);
        return result;
    }

    @Override
    public User get(Authentication authentication, long id) {
        //long userId = ((JwtUser) authentication.getPrincipal()).getId();
        return this.userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<User> getListUsers(Authentication authentication) {
        long userId = ((JwtUser) authentication.getPrincipal()).getId();
        return this.userRepository.findAll();
    }


}
