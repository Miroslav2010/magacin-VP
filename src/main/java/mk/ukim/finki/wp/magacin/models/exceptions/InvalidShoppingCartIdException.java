package mk.ukim.finki.wp.magacin.models.exceptions;

public class InvalidShoppingCartIdException extends RuntimeException{
    public InvalidShoppingCartIdException(){
        super("Invalid ShoppingCart id Exception");
    }
}
