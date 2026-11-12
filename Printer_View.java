import java.io.PrintStream;
import java.util.Scanner;

public class Printer_View {
    private final Scanner input;
    private final PrintStream output;

    public Printer_View() {
        this(new Scanner(System.in), System.out);
    }

    public Printer_View(Scanner input, PrintStream output) {
        if (input == null || output == null) {
            throw new IllegalArgumentException("Input and output are required.");
        }
        this.input = input;
        this.output = output;
    }

    public void showWelcome() {
        output.println("Welcome to Print Panic!");
        output.println("Print jobs in order, refill paper, and clear jams.");
        output.println("New jobs may arrive after every action, even invalid input.");
    }

    public void displayStatus(int currentPaper, int maxPaper,
        int pendingJobs, boolean jammed) {
        output.println();
        output.println("--- Printer Status ---");
        output.println("Paper: " + currentPaper + "/" + maxPaper);
        output.println("Pending jobs: " + pendingJobs);
        output.println("Status: " + (jammed ? "JAMMED" : "Ready"));
    }

    public int getUserChoice() {
        output.println();
        output.println("1. Print next job");
        output.println("2. Refill paper to maximum");
        output.println("3. Clear jam");
        output.println("0. Quit");
        output.print("Choose an action: ");
        output.flush();
        String line = input.nextLine();
        try {
            return Integer.parseInt(line.trim());
        }
        catch (NumberFormatException exception) {
            return -1;
        }
    }

    public void showMessage(String message) {
        output.println(message);
    }
}