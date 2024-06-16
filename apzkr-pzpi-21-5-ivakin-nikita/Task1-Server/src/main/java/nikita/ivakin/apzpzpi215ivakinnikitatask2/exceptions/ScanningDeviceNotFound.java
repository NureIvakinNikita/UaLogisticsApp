package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions;

public class ScanningDeviceNotFound extends RuntimeException {

    public ScanningDeviceNotFound(String message) {
        super(message);
    }

    public ScanningDeviceNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
