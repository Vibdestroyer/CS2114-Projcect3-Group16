import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Random;

public class Main {
    private static final int MAX_PAPER = 50;
    private static final int STARTING_JOBS = 3;
    private static final int MAX_PENDING_JOBS = 15;
    private static final double NEW_JOB_CHANCE = 0.50;
    private static final double JAM_CHANCE = 0.15;

    private final Printer printer;
    private final Printer_View view;
    private final Text_Reader textReader;
    private final Random random;
    private boolean running;

    public Main(Printer printer, Printer_View view, Text_Reader textReader,
        Random random) {
        if (printer == null || view == null || textReader == null
            || random == null) {
            throw new IllegalArgumentException("All game components are required.");
        }
        this.printer = printer;
        this.view = view;
        this.textReader = textReader;
        this.random = random;
        running = true;
    }

    public static void main(String[] args) {
        Printer_View view = new Printer_View();
        if (args.length != 0 && args.length != 2) {
            view.showMessage("Usage: java Main [namesFile descriptionsFile]");
            return;
        }

        String namesFile = args.length == 2 ? args[0] : "names.txt";
        String descriptionsFile = args.length == 2
            ? args[1] : "descriptions.txt";

        try {
            Text_Reader reader = new Text_Reader(namesFile, descriptionsFile);
            Printer printer = new Printer(MAX_PAPER);
            printer.addPaper(MAX_PAPER);
            for (int i = 0; i < STARTING_JOBS; i++) {
                printer.addJob(reader.createRandomJob());
            }

            Main game = new Main(printer, view, reader, new Random());
            game.runGame();
        }
        catch (IOException | IllegalArgumentException exception) {
            view.showMessage("Could not start the game: " + exception.getMessage());
            view.showMessage("Check the paths and contents of the text files.");
        }
    }

    public void runGame() {
        view.showWelcome();
        view.showMessage("You lose if more than " + MAX_PENDING_JOBS
            + " print jobs are waiting.");
        updateView();
        checkForLoss();

        while (running) {
            try {
                processChoice(view.getUserChoice());
            }
            catch (NoSuchElementException exception) {
                running = false;
                view.showMessage("Input ended. Thanks for playing Print Panic!");
            }
        }
    }

    public void processChoice(int choice) {
        if (!running || checkForLoss()) {
            return;
        }

        boolean clearedJam = false;
        switch (choice) {
            case 1:
                printNextJob();
                break;
            case 2:
                refillPaper();
                break;
            case 3:
                clearedJam = clearJam();
                break;
            case 0:
                running = false;
                view.showMessage("Thanks for playing Print Panic!");
                return;
            default:
                view.showMessage("Invalid option. Enter 0, 1, 2, or 3.");
                break;
        }

        if (random.nextDouble() < NEW_JOB_CHANCE) {
            generatePrintJob();
        }
        if (!clearedJam && !printer.isJammed()
            && random.nextDouble() < JAM_CHANCE) {
            printer.jam();
            view.showMessage("The printer has jammed!");
        }
        updateView();
        checkForLoss();
    }

    public void updateView() {
        view.displayStatus(printer.getCurrentPaper(), printer.getMaxPaper(),
            printer.getPendingJobs(), printer.isJammed());

        Print_Job nextJob = printer.getNextJob();
        if (nextJob != null) {
            view.showMessage("Next job: " + nextJob.getRequester() + " - "
                + nextJob.getDescription() + " (" + nextJob.getPages()
                + " pages)");
        }
        else {
            view.showMessage("There are no pending jobs.");
        }
    }

    public void generatePrintJob() {
        Print_Job job = textReader.createRandomJob();
        printer.addJob(job);
        view.showMessage("New job: " + job.getRequester() + " - "
            + job.getDescription() + " (" + job.getPages() + " pages)");
    }

    private boolean checkForLoss() {
        if (printer.getPendingJobs() > MAX_PENDING_JOBS) {
            running = false;
            view.showMessage("Game over! You have " + printer.getPendingJobs()
                + " pending jobs. The limit is " + MAX_PENDING_JOBS + ".");
            return true;
        }
        return false;
    }

    private void printNextJob() {
        Print_Job nextJob = printer.getNextJob();
        if (printer.printNextJob()) {
            view.showMessage("Printed " + nextJob.getPages() + " pages for "
                + nextJob.getRequester() + ": " + nextJob.getDescription());
        }
        else if (printer.isJammed()) {
            view.showMessage("Clear the paper jam before printing.");
        }
        else if (nextJob == null) {
            view.showMessage("There are no pending print jobs.");
        }
        else {
            view.showMessage("Not enough paper. The next job needs "
                + nextJob.getPages() + " sheets; you have "
                + printer.getCurrentPaper() + ".");
        }
    }

    private void refillPaper() {
        int amount = printer.getMaxPaper() - printer.getCurrentPaper();
        if (amount == 0) {
            view.showMessage("The paper tray is already full.");
            return;
        }
        printer.addPaper(amount);
        view.showMessage("Added " + amount + " sheets. The paper tray is full.");
    }

    private boolean clearJam() {
        if (!printer.isJammed()) {
            view.showMessage("The printer is not jammed.");
            return false;
        }
        printer.clearJam();
        view.showMessage("Paper jam cleared.");
        return true;
    }
}