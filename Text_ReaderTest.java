import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import student.TestCase;

public class Text_ReaderTest extends TestCase {
    private File tempNames;
    private File tempDescriptions;
    private File emptyFile;

    public void setUp() throws IOException {
        tempNames = File.createTempFile("names", ".txt");
        tempDescriptions = File.createTempFile("descriptions", ".txt");
        emptyFile = File.createTempFile("empty", ".txt");
        tempNames.deleteOnExit();
        tempDescriptions.deleteOnExit();
        emptyFile.deleteOnExit();

        FileWriter nameWriter = new FileWriter(tempNames);
        nameWriter.write("Victor\nChris\nMadison\n");
        nameWriter.close();

        FileWriter descWriter = new FileWriter(tempDescriptions);
        descWriter.write("Lab report\nHomework\n");
        descWriter.close();
    }


    public void testValidFiles() throws IOException {
        Text_Reader reader = new Text_Reader(tempNames.getAbsolutePath(),
            tempDescriptions.getAbsolutePath());
        List<String> validNames = Arrays.asList("Victor", "Chris", "Madison");
        List<String> validDescs = Arrays.asList("Lab report", "Homework");

        assertTrue(validNames.contains(reader.getRandomName()));
        assertTrue(validDescs.contains(reader.getRandomDescription()));

        Print_Job job = reader.createRandomJob();
        assertTrue(job.getPages() >= 1);
        assertTrue(job.getPages() <= 10);
        assertTrue(validNames.contains(job.getRequester()));
        assertTrue(validDescs.contains(job.getDescription()));
    }


    public void testNonexistentFile() {
        Exception exception = null;
        try {
            new Text_Reader("does_not_exist.txt", tempDescriptions
                .getAbsolutePath());
        }
        catch (IOException e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    public void testEmptyFile() throws IOException {
        Exception exception = null;
        try {
            new Text_Reader(emptyFile.getAbsolutePath(), tempDescriptions
                .getAbsolutePath());
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    public void testNullPath() throws IOException {
        Exception exception = null;
        try {
            new Text_Reader(null, tempDescriptions.getAbsolutePath());
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    public void testExceptionCases() {
        String validNames = tempNames.getAbsolutePath();
        String validDescs = tempDescriptions.getAbsolutePath();
        String[] namePaths = { validNames, "does_not_exist.txt", emptyFile
            .getAbsolutePath(), null, "   " };
        Class<?>[] expected = { null, IOException.class,
            IllegalArgumentException.class, IllegalArgumentException.class,
            IllegalArgumentException.class };

        for (int i = 0; i < namePaths.length; i++) {
            Exception caught = null;
            try {
                new Text_Reader(namePaths[i], validDescs);
            }
            catch (Exception e) {
                caught = e;
            }

            if (expected[i] == null) {
                assertNull(caught);
            }
            else {
                assertTrue(expected[i].isInstance(caught));
            }
        }
    }
}