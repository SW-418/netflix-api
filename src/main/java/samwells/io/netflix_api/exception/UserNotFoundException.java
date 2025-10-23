package samwells.io.netflix_api.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User not found or disabled");
    }
}
