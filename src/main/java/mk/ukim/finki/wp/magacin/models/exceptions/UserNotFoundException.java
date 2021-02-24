package mk.ukim.finki.wp.magacin.models.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){super("User not found exception"); }
}
