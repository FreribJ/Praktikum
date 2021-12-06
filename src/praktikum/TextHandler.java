package praktikum;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Handles all the console-interfaces.
 */
public class TextHandler {
    static Scanner sc = new Scanner(System.in).useDelimiter("\n");

    /**
     * Reads a user input from the console as a {@link String}.
     * @param message for a user input.
     * @return the user input as a {@link String}.
     */
    public static String getText(String message) {
        System.out.println();
        System.out.println(message);
        String input = sc.next();
        System.out.println();
        System.out.println("-".repeat(100));
        return input;
    }

    /**
     * Reads a user input from the console until it is a {@link Integer}
     * @param message for a user input
     * @return the user input as a {@link Integer}
     */
    public static int getInt(String message){
        System.out.println();
        System.out.println(message);
            try {
                int input = Integer.parseInt(sc.next());
                System.out.println();
                System.out.println("-".repeat(100));
                return input;
            }catch (NumberFormatException e){
                return getInt("you have to give a number:");
            }
    }

    /**
     * Reads a user input from the console until it is a {@link Double}
     * @param message for a user input
     * @return the user input as a {@link Double}
     */
    public static double getDouble(String message){
        System.out.println();
        System.out.println(message);
        try {
            double input = Double.parseDouble(sc.next());
            System.out.println();
            System.out.println("-".repeat(100));
            return input;
        }catch (NumberFormatException e){
            return getDouble("you have to give a double:");
        }
    }

    /**
     * Prints an output at the console in a unified design
     * @param text is the information, stored as a {@link String}.
     */
    public static void print(String text){
        var textList = new ArrayList<String>();
        textList.add(text);
        print(textList);
    }

    /**
     * Prints an output at the console in a unified design
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s.
     */
    public static void print(ArrayList<String> textList){
        print(textList, null);
    }

    /**
     * Prints an output at the console in a unified design
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s.
     * @param headline is for the headline above the information.
     */
    public static void print(ArrayList<String> textList, String headline){
        print(textList, headline, false);
    }

    /**
     * Prints an output at the console in a unified design
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s.
     * @param headline is for the headline above the information.
     * @param enumeration if enumeration is true all main information will be counted.
     */
    public static void print(ArrayList<String> textList, String headline,  boolean enumeration){
        print(textList, headline, null, enumeration, null);
    }

    /**
     * Prints an output at the console in a unified design
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s.
     * @param headline is for the headline above the information.
     * @param prefix is for a unified text in front of the main information.
     */
    public static void print(ArrayList<String> textList, String headline,  String prefix){
        print(textList, headline, prefix, false, null);
    }

    /**
     * Prints an output at the console in a unified design
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s.
     * @param enumeration if enumeration is true all main information will be counted.
     */
    public static void print(ArrayList<String> textList, boolean enumeration){
        print(textList, null, enumeration);
    }

    /**
     * Prints an output at the console in a unified design
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s.
     * @param headline is for the headline above the information.
     * @param prefix is for a unified text in front of the main information.
     * @param enumeration if enumeration is true all main information will be counted.
     * @param extraInformation is for extra information for every main information.
     *                         Extra information for one main information are stored as a {@link ArrayList} of {@link String}s.
     *                         Each extra information for one main information are also stored in a {@link ArrayList}.
     */
    public static void print(ArrayList<String> textList, String headline, String prefix, boolean enumeration, ArrayList<ArrayList<String>> extraInformation) throws RuntimeException{
        if(prefix == null)
                prefix = "";
        if(Objects.equals(headline, ""))
                headline = null;
        System.out.println();
        if(headline != null){
            System.out.println(headline + ":");
        }
        if(!prefix.equals("")){
            prefix = prefix + ": ";
        }
        for(int i = 0; i < textList.size(); i++) {
            System.out.println((enumeration ? i+1 + ". " : "") + prefix + textList.get(i));
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
