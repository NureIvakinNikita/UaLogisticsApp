package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class MilitaryGroupMappingToDtoException extends RuntimeException{

    public MilitaryGroupMappingToDtoException(String message) {
        super(message);
    }

    public MilitaryGroupMappingToDtoException(String message, Throwable cause) {
        super(message, cause);
    }
}
