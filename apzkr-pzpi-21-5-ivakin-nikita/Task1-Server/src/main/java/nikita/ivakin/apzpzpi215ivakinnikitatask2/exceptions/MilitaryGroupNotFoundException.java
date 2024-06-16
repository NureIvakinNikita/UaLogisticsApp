package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class MilitaryGroupNotFoundException extends RuntimeException{

    public MilitaryGroupNotFoundException(String message) {
        super(message);
    }

    public MilitaryGroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
