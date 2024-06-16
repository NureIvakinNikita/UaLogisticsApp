package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class CommanderAssigningException extends RuntimeException{

    public CommanderAssigningException(String message) {
        super(message);
    }

    public CommanderAssigningException(String message, Throwable cause) {
        super(message, cause);
    }
}
