package mk.ukim.finki.wp.magacin.models.exceptions;

public class InvalidOrderIdException extends RuntimeException{
    public InvalidOrderIdException(){
        super("Invalid Manufacturer id Exception");
    }
}
