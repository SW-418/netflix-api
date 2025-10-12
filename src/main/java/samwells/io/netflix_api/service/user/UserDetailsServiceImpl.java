package samwells.io.netflix_api.service.user;

import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.entity.User;
import samwells.io.netflix_api.exception.LoginFailedException;
import samwells.io.netflix_api.exception.UserAlreadyExistsException;
import samwells.io.netflix_api.repository.UserRepository;
import samwells.io.netflix_api.service.security.JwtService;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void createUser(UserDetails user) {
        try {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) throw new UserAlreadyExistsException();
            throw e;
        }
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) throw new LoginFailedException();

        return jwtService.generateJwt(user.getId());
    }
}
