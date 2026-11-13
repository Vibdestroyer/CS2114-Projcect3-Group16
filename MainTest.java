import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import student.TestCase;

public class MainTest extends TestCase {
    private Printer printer;
    private Text_Reader reader;
    private ByteArrayOutputStream outStream;
    private PrintStream printStream;
    private File tempNames;
    private File tempDescs;

    private static class FixedRandom extends Random {
        private static final long serialVersionUID = 1L;
        private final double value;

        public FixedRandom(double value) {
            this.value = value;
        }


        @Override
        public double nextDouble() {
            return value;
        }
    }

    public void setUp() throws IOException {
        tempNames = File.createTempFile("main_names", ".txt");
        tempDescs = File.createTempFile("main_descs", ".txt");
        tempNames.deleteOnExit();
        tempDescs.deleteOnExit();

        FileWriter nw = new FileWriter(tempNames);
        nw.write("Victor\n");
        nw.close();

        FileWriter dw = new FileWriter(tempDescs);
        dw.write("Project report\n");
        dw.close();

        reader = new Text_Reader(tempNames.getAbsolutePath(), tempDescs
            .getAbsolutePath());
        printer = new Printer(50);
        printer.addPaper(50);
        outStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outStream);
    }


    public void testConstructorNullChecks() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(new byte[0]));
        Printer_View view = new Printer_View(scanner, printStream);
        Random rand = new FixedRandom(0.99);

        Exception e1 = null;
        try {
            new Main(null, view, reader, rand);
        }
        catch (IllegalArgumentException e) {
            e1 = e;
        }
        assertNotNull(e1);

        Exception e2 = null;
        try {
            new Main(printer, null, reader, rand);
        }
        catch (IllegalArgumentException e) {
            e2 = e;
        }
        assertNotNull(e2);

        Exception e3 = null;
        try {
            new Main(printer, view, null, rand);
        }
        catch (IllegalArgumentException e) {
            e3 = e;
        }
        assertNotNull(e3);

        Exception e4 = null;
        try {
            new Main(printer, view, reader, null);
        }
        catch (IllegalArgumentException e) {
            e4 = e;
        }
        assertNotNull(e4);
    }


    public void testProcessActionsAndSkip() {
        Print_Job job1 = new Print_Job(5, "Victor", "First");
        Print_Job job2 = new Print_Job(5, "Chris", "Second");
        printer.addJob(job1);
        printer.addJob(job2);

        Scanner scanner = new Scanner(new ByteArrayInputStream(new byte[0]));
        Printer_View view = new Printer_View(scanner, printStream);
        // 0.99 prevents random new jobs (0.50) and random jams (0.15)
        Main game = new Main(printer, view, reader, new FixedRandom(0.99));

        // Skip job1 -> job2 moves to front
        game.processChoice(4);
        assertEquals(job2, printer.getNextJob());

        // Print job2 -> job1 moves to front
        game.processChoice(1);
        assertEquals(job1, printer.getNextJob());
        assertEquals(45, printer.getCurrentPaper());

        // Refill paper -> back to 50
        game.processChoice(2);
        assertEquals(50, printer.getCurrentPaper());

        // Clear jam when jammed
        printer.jam();
        game.processChoice(3);
        assertFalse(printer.isJammed());
    }


    public void testProcessActionsAndEdgeCases() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(new byte[0]));
        Printer_View view = new Printer_View(scanner, printStream);
        Main game = new Main(printer, view, reader, new FixedRandom(0.99));

        // Empty queue branches for print, skip, refill when full, and clearJam
        // when not jammed
        game.processChoice(1); // print empty
        game.processChoice(4); // skip empty
        game.processChoice(2); // refill full
        game.processChoice(3); // clear jam when not jammed

        // Jammed print branch
        Print_Job bigJob = new Print_Job(20, "Victor", "Big Report");
        Print_Job smallJob = new Print_Job(5, "Chris", "Notes");
        printer.addJob(bigJob);
        printer.addJob(smallJob);
        printer.jam();
        game.processChoice(1); // jammed

        // Clear jam when jammed
        game.processChoice(3);
        assertFalse(printer.isJammed());

        // Skip bigJob -> smallJob moves to front
        game.processChoice(4);
        assertEquals(smallJob, printer.getNextJob());

        // Print smallJob -> bigJob moves to front
        game.processChoice(1);
        assertEquals(bigJob, printer.getNextJob());
        assertEquals(45, printer.getCurrentPaper());

        // Refill paper when not full
        game.processChoice(2);
        assertEquals(50, printer.getCurrentPaper());

        // Not enough paper branch (drain paper with 50-page job, then try
        // 20-page job)
        printer.addJob(new Print_Job(50, "Madison", "Book"));
        game.processChoice(4); // skip bigJob -> 50-page job to front
        game.processChoice(1); // print 50-page job -> 0 paper left
        assertEquals(0, printer.getCurrentPaper());
        game.processChoice(1); // fails due to 0 paper vs 20 needed

        String output = outStream.toString();
        assertTrue(output.contains("There are no pending print jobs."));
        assertTrue(output.contains("There are no pending print jobs to skip."));
        assertTrue(output.contains("The paper tray is already full."));
        assertTrue(output.contains("The printer is not jammed."));
        assertTrue(output.contains("Clear the paper jam before printing."));
        assertTrue(output.contains("Not enough paper."));
    }


    public void testRunGameAndQuit() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("9\n0\n"
            .getBytes(StandardCharsets.UTF_8)));
        Printer_View view = new Printer_View(scanner, printStream);
        Main game = new Main(printer, view, reader, new FixedRandom(0.99));

        game.runGame();
        String output = outStream.toString();
        assertTrue(output.contains("Invalid option"));
        assertTrue(output.contains("Thanks for playing Print Panic!"));
    }


    public void testRandomJobJamAndLossCondition() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(new byte[0]));
        Printer_View view = new Printer_View(scanner, printStream);
        // 0.05 < 0.50 (triggers new job) and 0.05 < 0.15 (triggers jam)
        Main game = new Main(printer, view, reader, new FixedRandom(0.05));

        // Add 15 jobs so the next random job pushes pending jobs to 16 (>
        // MAX_PENDING_JOBS)
        for (int i = 0; i < 15; i++) {
            printer.addJob(new Print_Job(1, "User", "Job " + i));
        }

        game.processChoice(2); // triggers random job (16 jobs) + jam + game
                               // over
        assertTrue(printer.isJammed());
        assertTrue(outStream.toString().contains("Game over!"));

        // Calling processChoice after game over hits (!running) branch
        // immediately
        game.processChoice(1);
    }


    public void testRunGameQuitAndEOF() {
        // Normal quit with choice 0
        Scanner quitScanner = new Scanner(new ByteArrayInputStream("9\n0\n"
            .getBytes(StandardCharsets.UTF_8)));
        Printer_View view1 = new Printer_View(quitScanner, printStream);
        Main game1 = new Main(printer, view1, reader, new FixedRandom(0.99));
        game1.runGame();
        assertTrue(outStream.toString().contains(
            "Thanks for playing Print Panic!"));

        // Empty stream triggers NoSuchElementException in runGame()
        Scanner eofScanner = new Scanner(new ByteArrayInputStream(new byte[0]));
        Printer_View view2 = new Printer_View(eofScanner, printStream);
        Main game2 = new Main(printer, view2, reader, new FixedRandom(0.99));
        game2.runGame();
        assertTrue(outStream.toString().contains("Input ended."));
    }


    public void testMainEntryPoint() {
        InputStream origIn = System.in;
        PrintStream origOut = System.out;
        ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysOut));

        try {
            // 1. Invalid argument count
            Main.main(new String[] { "onlyOneArg.txt" });
            assertTrue(sysOut.toString().contains("Usage:"));

            // 2. Nonexistent files (IOException catch block)
            Main.main(new String[] { "bad_names.txt", "bad_descs.txt" });
            assertTrue(sysOut.toString().contains("Could not start the game"));

            // 3. Valid files with immediate quit ("0\n")
            System.setIn(new ByteArrayInputStream("0\n".getBytes(
                StandardCharsets.UTF_8)));
            Main.main(new String[] { tempNames.getAbsolutePath(), tempDescs
                .getAbsolutePath() });
            assertTrue(sysOut.toString().contains("Welcome to Print Panic!"));
        }
        finally {
            System.setIn(origIn);
            System.setOut(origOut);
        }
    }

}