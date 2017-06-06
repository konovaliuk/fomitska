package ua.training.exception;

public class FailedOperationException extends Exception {
    private String messageWithCause;

    public FailedOperationException() {
    }

    public FailedOperationException(String message, Throwable cause) {
        super(message, cause);
        this.messageWithCause = message;
    }

    public FailedOperationException(String message) {
        super(message);
        this.messageWithCause = message;
    }

    public FailedOperationException(Throwable cause) {
        super(cause);
    }

    public String getMessageWithCause() {
        return messageWithCause;
    }

    public void setCause(String messageWithCause) {
        this.messageWithCause = messageWithCause;
    }

}
