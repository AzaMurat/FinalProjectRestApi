package peaksoft.finalprojectrestapi.exception;

public class NotFountException extends RuntimeException {
    public NotFountException() {
    }

    public NotFountException(String message) {
        super(message);
    }
}
