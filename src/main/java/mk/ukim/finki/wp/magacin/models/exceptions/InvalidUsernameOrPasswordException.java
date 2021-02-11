package mk.ukim.finki.wp.magacin.models.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password");
    }
}

