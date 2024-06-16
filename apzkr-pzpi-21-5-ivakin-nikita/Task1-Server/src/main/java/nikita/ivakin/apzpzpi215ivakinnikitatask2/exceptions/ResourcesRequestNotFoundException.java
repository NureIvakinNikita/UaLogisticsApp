package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class ResourcesRequestNotFoundException extends RuntimeException{

    public ResourcesRequestNotFoundException(String message) {
        super(message);
    }

    public ResourcesRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
