package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class SupplyRequestNotFoundException extends RuntimeException{

    public SupplyRequestNotFoundException(String message) {
        super(message);
    }

    public SupplyRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
