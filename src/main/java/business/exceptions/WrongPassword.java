package business.exceptions;

public class WrongPassword extends Exception{
    public WrongPassword() {
    }

    public WrongPassword(String message) {
        super(message);
    }

    public WrongPassword(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPassword(Throwable cause) {
        super(cause);
    }

    public WrongPassword(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
