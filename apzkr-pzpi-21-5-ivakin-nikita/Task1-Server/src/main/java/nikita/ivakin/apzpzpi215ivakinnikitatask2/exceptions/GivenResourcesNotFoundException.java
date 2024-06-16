package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class GivenResourcesNotFoundException extends RuntimeException{

    public GivenResourcesNotFoundException(String message) {
        super(message);
    }

    public GivenResourcesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
