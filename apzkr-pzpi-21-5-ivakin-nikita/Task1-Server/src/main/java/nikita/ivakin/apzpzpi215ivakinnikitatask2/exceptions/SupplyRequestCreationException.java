package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class SupplyRequestCreationException extends RuntimeException{
    public SupplyRequestCreationException(String message) {
        super(message);
    }

    public SupplyRequestCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
