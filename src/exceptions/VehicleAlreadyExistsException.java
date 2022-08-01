package exceptions;

public class VehicleAlreadyExistsException extends RuntimeException {
    public VehicleAlreadyExistsException() {
        System.out.println("Vehicle Already Exists");
    }
}
