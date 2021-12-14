package praktikum.exceptions;

/**
 * Signals that data are not available.
 */
public class NotAvailableException extends Exception{

    /**
     * Creates a NotAvailableException with a specified message.
     * @param message the specified message of this {@link Exception}
     */
    public NotAvailableException(String message) {
        super(message);
    }
}
