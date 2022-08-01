package exceptions;

public class InvalidVehicleException extends RuntimeException{
    public InvalidVehicleException() {
        System.out.println("Invalid Vehicle");
    }
}
