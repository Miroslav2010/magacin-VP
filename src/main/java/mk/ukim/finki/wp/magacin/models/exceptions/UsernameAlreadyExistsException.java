package mk.ukim.finki.wp.magacin.models.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username) {
        super("User with username: "+username+" already exists");
    }
}
