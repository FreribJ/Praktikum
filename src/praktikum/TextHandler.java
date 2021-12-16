package praktikum;

import gmbh.kdb.hsw.gdp.domain.Skillset;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * Handles all the console-interfaces.
 */
public class TextHandler {
    static Scanner sc = new Scanner(System.in).useDelimiter("\n");

    /**
     * Reads a user input from the console as a {@link String}.
     *
     * @param message for a user input
     * @return the user input as a {@link String}
     */
    public static String getText(String message) {
        System.out.println();
        System.out.println(message);
        var input = sc.next();
        System.out.println();
        System.out.println("-".repeat(100));

        //Für Konsoleneingabe über Batch
        if (input.length() > 0 && input.toCharArray()[input.toCharArray().length - 1] == 13) {
            input = input.substring(0, input.length() - 1);
        }
        return input;
    }

    /**
     * Reads a user input from the console until it is a {@link Integer}.
     *
     * @param message for a user input
     * @param min     is the minimum of the range
     * @param max     is the maximum of the range
     * @return the user input as a {@link Integer}
     */
    public static int getInt(String message, int min, int max) {
        System.out.println();
        System.out.println(message);
        try {
            var input = sc.next();

            //Für Konsoleneingabe über Batch
            if (input.length() > 0 && input.toCharArray()[input.toCharArray().length - 1] == 13) {
                input = input.substring(0, input.length() - 1);
            }

            System.out.println();
            System.out.println("-".repeat(100));
            var inputInt = Integer.parseInt(input);
            if (inputInt < min || inputInt > max) {
                throw new NumberFormatException();
            }
            return inputInt;
        } catch (NumberFormatException e) {
            return getInt("You have to give a number between " + min + " and " + max + ".", min, max);
        }
    }

    /**
     * Reads a user input from the console until it is a {@link Double} in the given range.
     *
     * @param message for a user input
     * @param min     is the minimum of the range
     * @param max     is the maximum of the range
     * @return the user input as a {@link Double}
     */
    public static double getDouble(String message, double min, double max) {
        System.out.println();
        System.out.println(message);
        try {
            var input = sc.next();

            //Für Konsoleneingabe über Batch
            if (input.length() > 0 && input.toCharArray()[input.toCharArray().length - 1] == 13) {
                input = input.substring(0, input.length() - 1);
            }

            System.out.println();
            System.out.println("-".repeat(100));
            var inputDouble = Double.parseDouble(input);
            if (inputDouble < min || inputDouble > max) {
                throw new NumberFormatException();
            }
            return inputDouble;
        } catch (NumberFormatException e) {
            return getDouble("You have to give a double between " + min + " and " + max + ".", min, max);
        }
    }

    /**
     * Reads 4 skills to create a {@link Skillset}.
     *
     * @return the combined {@link Skillset}
     */
    public static Skillset getSkillset() {
        var skills = new int[4];
        String[] skillNames = {"Coding", "Research", "Testing", "Design"};
        var totalSkill = new Random().nextInt(30, 40);
        TextHandler.print(String.format("""
                In the following you are asked to enter 4 skills.
                The destiny has decided that the total skills for this applicant have a maximum of %s.
                Pay attention that the sum of all the values does not exceed %s.""", totalSkill, totalSkill));

        for (int i = 0; i < 4; i++) {
            if (totalSkill <= 0) {
                skills[i] = 0;
                TextHandler.print("The skill " + skillNames[i] + " is automatically 0 because the maximum is reached");
            } else {
                var message = String.format("""
                        You have a total skill of %s left.
                        %s skill [0 - %s]:""", totalSkill, skillNames[i], Math.min(totalSkill, 10));
                skills[i] = TextHandler.getInt(message, 0, Math.min(totalSkill, 10));
                totalSkill -= skills[i];
            }
        }

        return new Skillset(skills[0], skills[1], skills[2], skills[3]);
    }

    /**
     * Prints an output at the console in a unified design.
     *
     * @param text is the information, stored as a {@link String}
     */
    public static void print(String text) {
        var textList = new ArrayList<String>();
        textList.add(text);
        print(textList);
    }

    /**
     * Prints an output at the console in a unified design.
     *
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s
     */
    public static void print(ArrayList<String> textList) {
        print(textList, null);
    }

    /**
     * Prints an output at the console in a unified design.
     *
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s
     * @param headline is for the headline above the information
     */
    public static void print(ArrayList<String> textList, String headline) {
        print(textList, headline, false);
    }

    /**
     * Prints an output at the console in a unified design.
     *
     * @param textList    has the main information, stored as a {@link ArrayList} of {@link String}s
     * @param headline    is for the headline above the information
     * @param enumeration if enumeration is true all main information will be counted
     */
    public static void print(ArrayList<String> textList, String headline, boolean enumeration) {
        print(textList, headline, null, enumeration, null);
    }

    /**
     * Prints an output at the console in a unified design.
     *
     * @param textList has the main information, stored as a {@link ArrayList} of {@link String}s
     * @param headline is for the headline above the information
     * @param prefix   is for a unified text in front of the main information
     */
    public static void print(ArrayList<String> textList, String headline, String prefix) {
        print(textList, headline, prefix, false, null);
    }

    /**
     * Prints an output at the console in a unified design.
     *
     * @param textList    has the main information, stored as a {@link ArrayList} of {@link String}s
     * @param enumeration if enumeration is true all main information will be counted
     */
    public static void print(ArrayList<String> textList, boolean enumeration) {
        print(textList, null, enumeration);
    }

    /**
     * Prints an output at the console in a unified design.
     *
     * @param textList         has the main information, stored as a {@link ArrayList} of {@link String}s.
     * @param headline         is for the headline above the information
     * @param prefix           is for a unified text in front of the main information
     * @param enumeration      if enumeration is true all main information will be counted
     * @param extraInformation is for extra information for every main information
     *                         Extra information for one main information are stored as a {@link ArrayList} of {@link String}s
     *                         Each extra information for one main information are also stored in a {@link ArrayList}
     */
    public static void print(ArrayList<String> textList, String headline, String prefix, boolean enumeration, ArrayList<ArrayList<String>> extraInformation) throws RuntimeException {
        if (prefix == null)
            prefix = "";
        if (Objects.equals(headline, ""))
            headline = null;
        System.out.println();
        if (headline != null) {
            System.out.println(headline + ":");
        }
        if (!prefix.equals("")) {
            prefix = prefix + ": ";
        }
        for (int i = 0; i < textList.size(); i++) {
            System.out.println((enumeration ? i + 1 + ". " : "") + prefix + textList.get(i));
            if (extraInformation != null) {
                if (textList.size() != extraInformation.size()) {
                    throw new RuntimeException("textList and extraInformation must have the same length");
                }
                var extraInformation2D = extraInformation.get(i);
                for (String information : extraInformation2D) {
                    System.out.println("   " + information);
                }
            }
        }
        System.out.println();
        System.out.println("-".repeat(100));
    }
}
