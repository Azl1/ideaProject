package abdullaevaziz.service;


import abdullaevaziz.model.User;
import abdullaevaziz.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void addUser(User user) {
        add(user, "User has already added!");
    }


    private void add(User user, String s) {
        try {
            this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(s);
        }
    }

    @Override
    public User getLogin(String login, String password) {
        return this.userRepository.findAllByLoginAndPassword(login, password);
    }

    @Override
    public User get(long id) {
        //long userId = ((JwtUser) authentication.getPrincipal()).getId();
        return this.userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<User> getListUsers() {
        return this.userRepository.findAll();
    }

}
