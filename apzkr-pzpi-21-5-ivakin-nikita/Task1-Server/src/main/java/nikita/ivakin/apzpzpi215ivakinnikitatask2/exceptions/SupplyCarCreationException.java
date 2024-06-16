package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class SupplyCarCreationException extends RuntimeException{

    public SupplyCarCreationException(String message) {
        super(message);
    }

    public SupplyCarCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
