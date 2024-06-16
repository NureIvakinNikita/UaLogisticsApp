package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class ResourcesRequestCreationException extends RuntimeException{

    public ResourcesRequestCreationException(String message) {
        super(message);
    }

    public ResourcesRequestCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
