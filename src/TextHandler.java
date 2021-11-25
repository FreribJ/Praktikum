import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class TextHandler {
    static Scanner sc = new Scanner((System.in));

    //Returns input from console
    public static String getText(String message) {
        System.out.println();
        System.out.println(message);
        String input = sc.next().toLowerCase();
        System.out.println();
        System.out.println("-".repeat(100));
        return input;
    }

    public static void print(String text){
        var textList = new ArrayList<String>();
        textList.add(text);
        print(textList);
    }

    public static void print(ArrayList<String> textList){
        print(textList, null);
    }

    public static void print(ArrayList<String> textList, String headline){
        print(textList, headline, false);
    }

    public static void print(ArrayList<String> textList, String headline,  boolean enumeration){
        print(textList, headline, "", enumeration, null);
    }

    public static void print(ArrayList<String> textList, String headline,  String prefix){
        print(textList, headline, prefix, false, null);
    }

    public static void print(ArrayList<String> textList, boolean enumeration){
        print(textList, null, enumeration);
    }

    public static void print(ArrayList<String> textList, String headline, String prefix, boolean enumeration, ArrayList<ArrayList<String>> extraInformation) throws RuntimeException{
        System.out.println();
        if(headline != null){
            System.out.println(headline + ":");
        }
        if(prefix != ""){
            prefix = prefix + ": ";
        }
        for(int i = 0; i < textList.size(); i++) {
            System.out.println(prefix + (enumeration ? i+1 + ". "+ textList.get(i) : textList.get(i)));
            if(extraInformation != null){
                if(textList.size() != extraInformation.size()){
                    throw new RuntimeException("textList and extraInformation must have the same length");
                }
                var extraInformation2D = extraInformation.get(i);
                for(String information : extraInformation2D){
                    System.out.println("   " + information);
                }
            }
        }
        System.out.println();
        System.out.println("-".repeat(100));
    }
}
