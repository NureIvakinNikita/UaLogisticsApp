package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class CommanderAuthenticationException extends RuntimeException{

    public CommanderAuthenticationException(String message) {
        super(message);
    }

    public CommanderAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
