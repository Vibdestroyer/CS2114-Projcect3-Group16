import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import student.TestCase;

public class Printer_ViewTest extends TestCase {
    private ByteArrayOutputStream outStream;
    private PrintStream printStream;

    public void setUp() {
        outStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outStream);
    }


    public void testNullConstructorArgs() {
        Exception exception = null;
        try {
            new Printer_View(null, printStream);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    public void testValidAndInvalidUserChoice() {
        String simulatedInput = "4\nabc\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput
            .getBytes(StandardCharsets.UTF_8)));
        Printer_View view = new Printer_View(scanner, printStream);

        assertEquals(4, view.getUserChoice());
        assertEquals(-1, view.getUserChoice());
    }


    public void testDisplayStatusAndMessages() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(new byte[0]));
        Printer_View view = new Printer_View(scanner, printStream);

        view.showWelcome();
        view.displayStatus(25, 50, 3, true);
        view.showMessage("Test alert");

        String output = outStream.toString();
        assertTrue(output.contains("Welcome to Print Panic!"));
        assertTrue(output.contains("Paper: 25/50"));
        assertTrue(output.contains("Pending jobs: 3"));
        assertTrue(output.contains("JAMMED"));
        assertTrue(output.contains("Test alert"));
    }
}