package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class SupplyCarNotFoundException extends RuntimeException{

    public SupplyCarNotFoundException(String message) {
        super(message);
    }

    public SupplyCarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
