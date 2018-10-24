package ltd.ontsol.core.exception;

/**
 * Created by cn40580 at 2018-06-17 10:27 AM.
 */
public class CustomException extends RuntimeException {

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
