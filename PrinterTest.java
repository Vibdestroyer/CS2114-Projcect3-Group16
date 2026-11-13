import student.TestCase;

public class PrinterTest extends TestCase {
    private Printer printer;

    public void setUp() {
        printer = new Printer(150);
    }


    public void testPaperInitial() {
        assertEquals(0, printer.getCurrentPaper());
        assertEquals(150, printer.getMaxPaper());
    }


    public void testAddPaper() {
        printer.addPaper(50);
        assertEquals(50, printer.getCurrentPaper());

        printer.addPaper(35);
        assertEquals(85, printer.getCurrentPaper());
    }


    public void testPrinterJobNext() {
        printer.addPaper(40);
        Print_Job jobnum1 = new Print_Job(10, "Madison", "Report");
        printer.addJob(jobnum1);

        assertTrue(printer.printNextJob());
        assertEquals(30, printer.getCurrentPaper());
        assertEquals(0, printer.getPendingJobs());
    }


    public void testPrinterNotSufficientPaper() {
        printer.addPaper(5);
        Print_Job jobnum1 = new Print_Job(10, "Madison", "Photo");
        printer.addJob(jobnum1);
        assertFalse(printer.printNextJob());
        assertEquals(5, printer.getCurrentPaper());
        assertEquals(1, printer.getPendingJobs());
        assertEquals(jobnum1, printer.getNextJob());
        printer.addPaper(10);
        assertTrue(printer.printNextJob());
    }


    public void testJamPrint() {
        printer.addPaper(20);
        Print_Job jobnum1 = new Print_Job(5, "Chris", "Picture");
        printer.addJob(jobnum1);
        printer.jam();

        assertTrue(printer.isJammed());
        assertFalse(printer.printNextJob());

        assertEquals(20, printer.getCurrentPaper());
        assertEquals(1, printer.getPendingJobs());
        assertEquals(jobnum1, printer.getNextJob());
    }


    public void testEmptyQueue() {
        printer.addPaper(30);
        assertEquals(0, printer.getPendingJobs());
        assertFalse(printer.printNextJob());
        assertEquals(30, printer.getCurrentPaper());

        assertEquals(0, printer.getPendingJobs());
        assertNull(printer.getNextJob());
    }


    public void testInvalidCapacity() {
        assertIllegalArgument(() -> new Printer(1), false);
        assertIllegalArgument(() -> new Printer(0), true);
    }


    public void testInvalidAddPaper() {
        assertIllegalArgument(() -> printer.addPaper(0), true);
        assertIllegalArgument(() -> printer.addPaper(151), true);
    }


    public void testClearJam() {
        printer.addPaper(20);
        Print_Job job = new Print_Job(5, "Chris", "Notes");
        printer.addJob(job);
        printer.jam();
        assertTrue(printer.isJammed());

        printer.clearJam();
        assertFalse(printer.isJammed());
        assertTrue(printer.printNextJob());
    }


    public void testAddNullJob() {
        assertIllegalArgument(() -> printer.addJob(null), true);
    }


    public void testSkipNextJobEmpty() {
        assertFalse(printer.skipNextJob());
        assertEquals(0, printer.getPendingJobs());
        assertNull(printer.getNextJob());
    }


    public void testSkipNextJobSingleJob() {
        Print_Job job1 = new Print_Job(5, "Madison", "Notes");
        printer.addJob(job1);

        assertTrue(printer.skipNextJob());
        assertEquals(1, printer.getPendingJobs());
        assertEquals(job1, printer.getNextJob());
    }


    public void testSkipNextJobMultipleJobs() {
        printer.addPaper(50);
        Print_Job job1 = new Print_Job(5, "Madison", "Notes");
        Print_Job job2 = new Print_Job(8, "Chris", "Slides");
        Print_Job job3 = new Print_Job(3, "Victor", "Syllabus");
        printer.addJob(job1);
        printer.addJob(job2);
        printer.addJob(job3);

        assertTrue(printer.skipNextJob());
        assertEquals(3, printer.getPendingJobs());
        assertEquals(job2, printer.getNextJob());

        assertTrue(printer.printNextJob());
        assertEquals(job3, printer.getNextJob());
        assertTrue(printer.printNextJob());
        assertEquals(job1, printer.getNextJob());
        assertTrue(printer.printNextJob());
        assertEquals(0, printer.getPendingJobs());
    }


    private void assertIllegalArgument(Runnable action, boolean shouldThrow) {
        Exception exception = null;
        try {
            action.run();
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertEquals(shouldThrow, exception != null);
    }
}
