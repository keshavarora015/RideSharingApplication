package exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException() {
        super("Invalid User!!");
    }
}
