package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class CommanderDoesNotAssignedException extends RuntimeException{

    public CommanderDoesNotAssignedException(String message) {
        super(message);
    }

    public CommanderDoesNotAssignedException(String message, Throwable cause) {
        super(message, cause);
    }
}
