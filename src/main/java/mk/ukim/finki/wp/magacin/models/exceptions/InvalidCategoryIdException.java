package mk.ukim.finki.wp.magacin.models.exceptions;

public class InvalidCategoryIdException extends RuntimeException{
    public InvalidCategoryIdException(){
        super("Invalid Category Id Exception");
    }
}
