import student.TestCase;

public class Print_JobTest extends TestCase{

    private Print_Job jobnum1;
    private Print_Job jobnum2;
    private Print_Job jobnum3;

    public void setUp()
    {
        jobnum1= new Print_Job(3, "Madison", "Linear algebra homework due in ten minutes");
        jobnum2= new Print_Job(5, "Alex", "Data structures project documentation");
        jobnum3= new Print_Job(11, "Sam", "Lost water bottle posters");
    }

    public void testGetPages()
    {
        assertEquals(3, jobnum1.getPages());
        assertEquals(5, jobnum2.getPages());
        assertEquals(11, jobnum3.getPages());
    }

    public void testGetRequester()
    {
        assertEquals("Madison", jobnum1.getRequester());
        assertEquals("Alex", jobnum2.getRequester());
        assertEquals("Sam", jobnum3.getRequester());
    }

    public void testGetDescription()
    {
        assertEquals("Linear algebra homework due in ten minutes", jobnum1.getDescription());
        assertEquals("Data structures project documentation", jobnum2.getDescription());
        assertEquals("Lost water bottle posters", jobnum3.getDescription());
    }

    public void testOnePage()
    {
        Print_Job jobnew= new Print_Job(1, "Taylor", "Chemistry lab report with suspicious results");
        assertEquals(1, jobnew.getPages());

        assertEquals("Taylor", jobnew.getRequester());
        assertEquals("Chemistry lab report with suspicious results", jobnew.getDescription());
    }

    public void testZeroPages()
    {
        Exception exception=null;

        try{
            new_Print_Job(0, "Avery", "Concert tickets for Friday night");
        }catch(IllegalArgumentException e)
        {
            exception=e;
        }
        assertNotNull(exception);
    }
    
    public void testNegativePages()
    {
        Exception exception=null;

        try{
            new Print_Job(-2, "Quinn", "Birthday card for a roommate");

        }
        catch(IllegalArgumentException e)
        {
            exception=e;
        }
        assertNotNull(exception);
        }
}

    public void testNullRequester()
    {
        Exception exception= null;

        try{
            new Print_Job(3, null, "Dorm cleaning schedule nobody follows");
        }
        catch(IllegalArgumentException e)
        {
            exception=e;
        }
        assertNotNull(exception);
    }

    public void testGetEmptyRequester()
    {
        Exception exception=null;

        try{
            new Print_Job(4, " ", "Calculus practice problems and solutions");
        }
        catch(IllegalArgumentException e)
        {
            exception=e;
        }
        assertNotNull(exception);
    }




    public void testElevenPages()
    {
        assertEquals(11, jobnum3.getPages());
    }

    public void testDifferentJobs()
    {
        assertEquals(3, jobnum1.getPages());
        assertEquals(5, jobnum2.getPages());
        assertEquals(11, jobnum3.getPages());
    }

    public void testIndepJobs()
    {
        Print_Job jobnew= new Print_Job(7, "Taylor", "Chemistry lab report with suspicious results");
        assertEquals(7, jobnew.getPages());

        assertEquals(3, jobnum1.getPages());
    }