package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class CommanderNotFoundException extends RuntimeException{

    public CommanderNotFoundException(String message) {
        super(message);
    }

    public CommanderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
