import student.TestCase;

public class Print_JobTest extends TestCase {

    private Print_Job jobnum1;
    private Print_Job jobnum2;
    private Print_Job jobnum3;

    public void setUp() {
        jobnum1 = new Print_Job(3, "Madison",
            "Linear algebra homework due in ten minutes");
        jobnum2 = new Print_Job(5, "Alex",
            "Data structures project documentation");
        jobnum3 = new Print_Job(11, "Sam", "Lost water bottle posters");
    }


    public void testGetPages() {
        assertEquals(3, jobnum1.getPages());
        assertEquals(5, jobnum2.getPages());
        assertEquals(11, jobnum3.getPages());
    }


    public void testGetRequester() {
        assertEquals("Madison", jobnum1.getRequester());
        assertEquals("Alex", jobnum2.getRequester());
        assertEquals("Sam", jobnum3.getRequester());
    }


    public void testGetDescription() {
        assertEquals("Linear algebra homework due in ten minutes", jobnum1
            .getDescription());
        assertEquals("Data structures project documentation", jobnum2
            .getDescription());
        assertEquals("Lost water bottle posters", jobnum3.getDescription());
    }


    public void testOnePage() {
        Print_Job jobnew = new Print_Job(1, "Taylor",
            "Chemistry lab report with suspicious results");
        assertEquals(1, jobnew.getPages());

        assertEquals("Taylor", jobnew.getRequester());
        assertEquals("Chemistry lab report with suspicious results", jobnew
            .getDescription());
    }


    public void testZeroPages() {
        assertIllegalArgument(() -> new Print_Job(1, "Avery", "Valid job"),
            false);
        assertIllegalArgument(() -> new Print_Job(0, "Avery",
            "Concert tickets for Friday night"), true);
    }


    public void testNegativePages() {
        assertIllegalArgument(() -> new Print_Job(-2, "Quinn",
            "Birthday card for a roommate"), true);
    }


    public void testNullRequester() {
        assertIllegalArgument(() -> new Print_Job(3, null,
            "Dorm cleaning schedule nobody follows"), true);
    }


    public void testGetEmptyRequester() {
        assertIllegalArgument(() -> new Print_Job(4, " ",
            "Calculus practice problems and solutions"), true);
    }


    public void testGetBlankRequester() {
        assertIllegalArgument(() -> new Print_Job(4, "",
            "Calculus practice problems and solutions"), true);
    }


    public void testNullDescription() {
        assertIllegalArgument(() -> new Print_Job(3, "Cameron", null), true);
    }


    public void testEmptyDescription() {
        assertIllegalArgument(() -> new Print_Job(4, "Casey", ""), true);
    }


    public void testBlankDescription() {
        assertIllegalArgument(() -> new Print_Job(6, "Riley", " "), true);
    }


    public void testIndepJobs() {
        Print_Job jobnew = new Print_Job(7, "Taylor",
            "Chemistry lab report with suspicious results");
        assertEquals(7, jobnew.getPages());

        assertEquals(3, jobnum1.getPages());
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
