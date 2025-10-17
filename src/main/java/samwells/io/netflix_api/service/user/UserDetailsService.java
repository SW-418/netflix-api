package samwells.io.netflix_api.service.user;

import org.springframework.security.provisioning.UserDetailsManager;

public interface UserDetailsService extends UserDetailsManager {
    String login(String username, String password);
    boolean userExists(Long userId);
}
