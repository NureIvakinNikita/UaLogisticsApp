package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class SupplyRequestUpdateException extends RuntimeException{

    public SupplyRequestUpdateException(String message) {
        super(message);
    }

    public SupplyRequestUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
