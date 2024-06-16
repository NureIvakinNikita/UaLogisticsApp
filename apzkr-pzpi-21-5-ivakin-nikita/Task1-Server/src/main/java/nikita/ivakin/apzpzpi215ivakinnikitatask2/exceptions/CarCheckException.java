package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class CarCheckException extends RuntimeException{

    public CarCheckException(String message) {
        super(message);
    }

    public CarCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
