import java.util.Scanner;

public class TextHandler {
    static Scanner sc = new Scanner((System.in));

    //Gibt input im Lower-Case aus
    public static String getText() {
        return sc.next().toLowerCase();
    }
}
