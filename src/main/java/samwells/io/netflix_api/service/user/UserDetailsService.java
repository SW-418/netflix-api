package samwells.io.netflix_api.service.user;

import org.springframework.security.provisioning.UserDetailsManager;
import samwells.io.netflix_api.entity.User;

public interface UserDetailsService extends UserDetailsManager {
    String login(String username, String password);
    boolean userExists(Long userId);
    User getReferenceById(Long userId);
}
