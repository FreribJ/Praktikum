public class WrongInputException extends Exception{
    public WrongInputException(String message) {
        super("Wrong input format: " + message);
    }
}
