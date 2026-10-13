import java.util.Scanner;

/** Handles all keyboard input and validation so the rest of the program stays clean. */
public class InputHelper {
    private static final Scanner SC = new Scanner(System.in);

    private static String readLine(String prompt) {
        System.out.print(prompt);
        if (!SC.hasNextLine()) {
            System.out.println("\nNo more input. Exiting.");
            System.exit(0);
        }
        return SC.nextLine().trim();
    }

    /** Keeps asking until the user types a valid whole number. */
    public static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Keeps asking until the number is between min and max (inclusive). */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    /** Keeps asking until the user types something that is not empty. */
    public static String readString(String prompt) {
        while (true) {
            String s = readLine(prompt);
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Input cannot be empty.");
        }
    }
}
