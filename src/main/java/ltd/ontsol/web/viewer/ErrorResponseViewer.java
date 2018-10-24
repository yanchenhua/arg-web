package ltd.ontsol.web.viewer;

/**
 * Created by cn40580 at 2018-06-17 10:28 AM.
 */
public class ErrorResponseViewer {
    private int code;
    private String message;

    public ErrorResponseViewer(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
