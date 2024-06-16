package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class CommanderSendingResourcesException extends RuntimeException{

    public CommanderSendingResourcesException(String message) {
        super(message);
    }

    public CommanderSendingResourcesException(String message, Throwable cause) {
        super(message, cause);
    }
}
