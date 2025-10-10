package samwells.io.netflix_api.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Login failed - Please check username and password");
    }
}
