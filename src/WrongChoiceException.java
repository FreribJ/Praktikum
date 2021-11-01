import java.rmi.server.ExportException;

public class WrongChoiceException extends ExportException {
    public WrongChoiceException(String s) {
        super(s);
    }
}
