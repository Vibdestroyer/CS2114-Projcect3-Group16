import student.TestCase;

public class PrinterTest extends TestCase {
    private Printer printer;

    public void setUp()
    {
        printer= new Printer(150);
    }

    public void testPaperInitial()
    {
        assertEquals(0, printer.getPaperCount());
        assertEquals(150, printer.getMaxPaperCount());
    }

    public void testAddPaper()
    {
        printer.addPaper(50);
        assertEquals(50, printer.getPaperCount());
        printer.addPaper(35);
        assertEquals(85, printer.getPaperCount());
    }

    public void testPrinterJobNext()
    {
        printer.addPaper(40);
        Print_Job jobnum1= new Print_Job(10, "Madison", "Report");
        printer.addJob(jobnum1);
        assertTrue(printer.printNextJob());
        assertEquals(30, printer.getPaperCount());
        assertEquals(0, printer.getPendingJobs());
    }

    public void testPrinterNotSufficientPaper()
    {
        printer.addPaper(5);
        Print_Job jobnum1= new Print_Job(10, "Madison", "Photo");
        printer.addJob(jobnum1);
        assertFalse(printer.printNextJob());
        assertEquals(5, printer.getPaperCount());
        assertEquals(1, printer.getPendingJobs());
        assertEquals(jobnum1, printer.getNextJob());
        printer.addPaper(10);
        assertTrue(printer.printNextJob());
    }
    public void testJamPrint()
    {
        printer.addPaper(20);
        Print_Job jobnum1=new Print_Job(5, "Chris", "Picture");
        printer.addJob(jobnum1);
        printer.jam();

        assertTrue(printer.isJammed());
        assertFalse(printer.printNextJob());

        assertEquals(20, printer.getPaperCount());
        assertEquals(1, printer.getPendingJobs());
        assertEquals(job, printer.getNextJob());
    }
    public void testEmptyQueue()
    {
        printer.addPaper(30);
        assertEquals(0, printer.getPendingJobs());
        assertFalse(printer.printNextJob());
        assertEquals(30, printer.getPaperCount());

        assertEquals(0, printer.getPendingJobs());
        assertNull(printer.getNextJob());
    }



}