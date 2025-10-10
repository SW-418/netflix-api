package samwells.io.netflix_api.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Could not create user - Please login if you already have an account.");
    }
}
