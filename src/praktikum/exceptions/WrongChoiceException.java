package praktikum.exceptions;

/**
 * Signals that an input was wrong.
 */
public class WrongChoiceException extends Exception{

    /**
     * Creates a WrongChoiceException with a standard message.
     */
    public WrongChoiceException() {
        super("Wrong input. Please try again");
    }
}
