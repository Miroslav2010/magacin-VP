package mk.ukim.finki.wp.magacin.models.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException() {
        super("Passwords do not match exception.");
    }
}

