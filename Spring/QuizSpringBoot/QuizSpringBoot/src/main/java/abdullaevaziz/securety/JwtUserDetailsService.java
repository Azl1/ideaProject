package abdullaevaziz.securety;


import abdullaevaziz.model.User;
import abdullaevaziz.repository.UserRepository;
import abdullaevaziz.securety.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByLogin(login);

        user.orElseThrow(() -> new UsernameNotFoundException("User with username: " + login + " not found"));

        log.info("IN loadUserByUserLogin - user with username: {} successfully loaded", login);
        return user.map(JwtUser::new).get();
    }
}
