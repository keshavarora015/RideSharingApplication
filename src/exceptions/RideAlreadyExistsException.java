package exceptions;

public class RideAlreadyExistsException extends RuntimeException{
    public RideAlreadyExistsException() {
        super("Ride Already Exists");
    }
}
