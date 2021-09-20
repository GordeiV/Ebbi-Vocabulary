package business.exceptions;

public class NoUserFound extends Exception{
    public NoUserFound() {
    }

    public NoUserFound(String message) {
        super(message);
    }

    public NoUserFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserFound(Throwable cause) {
        super(cause);
    }

    public NoUserFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
