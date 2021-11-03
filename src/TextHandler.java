import java.util.Scanner;

public class TextHandler {
    static Scanner sc = new Scanner((System.in));

    //Returns input from console
    public static String getText(String message) {
        System.out.println(message);
        return sc.next().toLowerCase();
    }
}
