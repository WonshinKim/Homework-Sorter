// import statements here...
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Homework Sorter (for CS 193 Final Project) -
 * This program...
 *
 * @author Wonshin Kim
 *
 * @version November 24, 2026 (not finished yet)
 *
 */
public class HomeworkSorter {
    public static void main(String[] args) {
        boolean done = false;
        String[] parts;
        String hwName;
        LocalDate hwDate;
        ArrayList<String> entries = new ArrayList<>();
        System.out.println("\nWelcome to Homework Sorter!");
        while (!done) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter a homework title (no spaces in title), a space,  and a due date with format yyyy-mm-dd");
            System.out.println("Example: 'EnglishEssay 2026-03-30'");
            System.out.println("(or enter 'done' when you are done entering homework entries)\n");
            String input = scan.nextLine();
            if (input.equals("done")) {
                done = true;
                System.out.println("\nThank you for using Homework Sorter!");
            } else {
                try {
                    parts = input.split(" ");
                    hwName = parts[0];
                    hwDate = LocalDate.parse(parts[1]);
                    System.out.println("\ndate conversion is a success!");
                    System.out.printf("homework title: %s\n", hwName);
                    System.out.printf("date: %s\n\n", hwDate);
                    if (entries.isEmpty()) {
                        entries.add(input);
                    } else {
                        int insertionIndex = 0;
                        for (String entry : entries) {
                            String[] pieces = entry.split(" ");
                            LocalDate entryDate = LocalDate.parse(pieces[1]);
                            if (hwDate.isAfter(entryDate)) {
                                insertionIndex++;
                            }
                        }
                        entries.add(insertionIndex, input);
                    }

                    System.out.println("arraylist: ");

                    for (String en : entries) {
                        System.out.println(en);
                    }

                    System.out.println("\n");

                } catch (DateTimeParseException d) {
                    System.out.println("\ninvalid input format (use a title with no spaces, a single space, and a date with a " +
                            "yyyy-mm-dd format { ex. MathHomework1 2027-01-01 } )");
                    System.out.println("check if input has this format: 'HomeworkName yyyy-mm-dd'\n");
                } catch (ArrayIndexOutOfBoundsException a) {
                    if (!input.isEmpty()) {
                        System.out.println("\nmake sure that there is a homework title and a date separated by a single space\n");
                    } else {
                        System.out.println("\nplease do not enter an empty line, type 'done' when you are done\n");
                    }
                }
            }
        }
    }
}