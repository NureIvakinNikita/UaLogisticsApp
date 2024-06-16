package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.errorResponse;

public record ValidationExceptionResponse(
        String errorCode,
        String errorMessage
) {
    public ValidationExceptionResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}