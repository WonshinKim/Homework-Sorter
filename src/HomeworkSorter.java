import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
// note: java.time uses Java 8 version; all other features can be used by older versions

/**
 * Homework Sorter (v 2.0) (for CS 193 Final Project) -
 * This program helps me sort out my homework assignments by their due dates,
 * placing the entries with earlier deadlines on top. First, the program displays
 * a welcome message. Then, the program enters into a loop where the program keeps
 * on asking the user for homework entries until the user types "done". Each homework
 * entry typed in must have the proper format: the name of the homework (not separated
 * by any spaces), a single space, and then the date with the exact format "yyyy-mm-dd".
 * (Example: HomeworkName 2027-05-09) If the entry does not have the correct format,
 * the entry cannot be included in a list and the user will be notified. If the homework
 * is correct, then the entry will be temporarily stored in the ArrayList. The entry will
 * be placed in its proper location in the ArrayList using a sorter method. Once the user
 * types "done", the program will convert the ArrayList into a txt file that will be
 * visible to the user inside the src file. After the txt file is created, the program
 * will tell the user the file's name and then print the goodbye message.
 * - Added feature: when user enters "peek", program will show the list to allow the user
 * to check if list appears as intended.
 * - Note: name can include any symbol and character (except a space),
 * and a "label" can be included on a new entry after the date as long as it's separated by a space
 * (example: HomeworkName 2027-05-09 ExtraLabel)
 *
 * @author Wonshin Kim
 *
 * @version November 27, 2026 (v 2.0)
 *
 */
public class HomeworkSorter {
    public static void main(String[] args) {
        boolean done = false;
        String[] parts;         // will hold the two parts of each entry (name and date) (makes date parsing easier)
        // used ArrayList because it is easier to add elements to than normal arrays
        ArrayList<String> entries = new ArrayList<>();
        Scanner scan = new Scanner(System.in);      // Scanner used to receive input
        System.out.println("\nWelcome to Homework Sorter!");
        while (!done) {             // repeats until user types "done"
            System.out.println("Enter a homework title (no spaces in title), a space," +
                    " and a due date with format yyyy-mm-dd");
            System.out.println("Example: 'EnglishEssay 2026-03-30'");
	        System.out.println("enter 'peek' to take a look at the list before you're done");
            System.out.println("(or enter 'done' when you are done entering homework entries)\n");
            String input = scan.nextLine();
            if (input.equals("done")) {  // when done, loop ends and ArrayList is copied into txt file
                done = true;
                try {
                    makeFile(entries, "HomeworkList.txt");  // method to convert list into file
                } catch (IOException io) {  // if file conversion fails
                    System.out.println("error in creating file");
                }
                System.out.println("\nYour homework entries are in file: HomeworkList.txt");
                System.out.println("\nThank you for using Homework Sorter!");
            } else if (input.equals("peek")) {  // shows list
                System.out.println("\nlist of homework entries:");
                for (String en: entries) {
                    System.out.println(en);
                }
                System.out.println("\n");
            } else {
                try {
                    parts = input.split(" ");  // splits entry into its parts
                    LocalDate.parse(parts[1]);  // second part is converted to a date

                    sorter(entries, input);         // method to add each entry in its proper place in ArrayList
                    System.out.print("\n");

                } catch (DateTimeParseException d) {  // if time conversion fails:
                    System.out.println("\ninvalid input format (use a title with no spaces, a single space, " +
                            "and a date with a yyyy-mm-dd format { ex. MathHomework1 2027-01-01 } )\n");
                } catch (ArrayIndexOutOfBoundsException a) {  // if line is empty or another format issue has occurred
                    if (!input.isEmpty()) {
                        System.out.println("\nmake sure that there is a homework title " +
                                "and a date separated by a single space\n");
                    } else {
                        System.out.println("\nplease do not enter an empty line, type 'done' when you are done\n");
                    }
                }
            }
        }
    }

    /**
     * Places each new entry in its proper place in the ArrayList (sorts by dates).
     *
     * @param list the ArrayList to place the entry in
     * @param newEntry the new entry (will be split into name and date to compare dates)
     */
    public static void sorter(ArrayList<String> list, String newEntry) {
        if (list.isEmpty()) {       // if list is empty, no sorting needed, just insert the new entry
            list.add(newEntry);
        } else {
            int insertionIndex = 0;  // determines where the new entry belongs in the list
            String[] slices = newEntry.split(" ");
            // note: checking for invalid dates was already done in main; no need to check again
            LocalDate newDate = LocalDate.parse(slices[1]);
            // below: compares the new entry's date to each date in the list
            for (String entry : list) {
                String[] pieces = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(pieces[1]);
                if (newDate.isAfter(entryDate)) {
                    insertionIndex++;
                }
            }
            list.add(insertionIndex, newEntry);
        }
    }

    /**
     * copies the ArrayList into a txt file
     *
     * @param list the ArrayList to be copied
     * @param fileName the file will take on "fileName" as its name
     * @throws IOException thrown onto main; main catches it if there is a file conversion error
     */
    public static void makeFile(ArrayList<String> list, String fileName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName))) {
            for (String entry : list) {
                pw.println(entry);
            }
        }
    }
}
