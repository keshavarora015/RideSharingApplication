package exceptions;

public class NoRideFoundException extends RuntimeException{
    public NoRideFoundException() {
        System.out.println("Ride Not Found");
    }
}
