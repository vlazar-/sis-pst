package Client;

/**
 * Functions for handling errors
 * Created by Viktor on 08/01/2015.
 */
public class ErrorHandler {

    private String err;

    public ErrorHandler(String message, String... args) {
        this.err = String.format(message, args);
    }

    public ErrorHandler(Exception e) {
        this.err = e.getMessage();
    }

    public String getErr() {
        return this.err;
    }
}
