import student.TestCase;

public class QueueTest extends TestCase {
    private Queue<Print_Job> queue;
    private Print_Job jobnum1;
    private Print_Job jobnum2;

    public void setUp()
    {
        queue= new Queue<Print_Job>();
        jobnum1=new Print_Job(4, "Madison", "Paper");
        jobnum2=new Print_Job(5, "Chris", "Presentation");
    }

    public void testQueueEmpty()
    {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());
    }

    public void testDequeue()
    {
        queue.enqueue(jobnum1);
        queue.enqueue(jobnum2);
        assertEquals(2, queue.size());
        assertEquals(jobnum1, queue.dequeue());

        assertEquals(1, queue.size());
        assertEquals(jobnum2, queue.peek());
    }

    public void testEnqueue()
    {
        queue.enqueue(jobnum1);
        aassertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals(jobnum1, queue.peek());
 
    }

    public void testFirstInFirstOut()
    {
        queue.enqueue(jobnum1);
        queue.enqueue(jobnum2);
        assertEquals(jobnum1, queue.dequeue());
        assertEquals(jobnum2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    public void testPeekEmpty()
    {
        assertNull(queue.peek());
        assertEquals(0, queue.size());
    }

    public void testEmptyDequeue()
    {

        assertNull(queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    public void testSize()
    {
        assertEquals(0, queue.size());
        queue.enqueue(jobnum1);
        assertEquals(1, queue.size());
        queue.enqueue(jobnum2);
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEqualsl(1, queue.size());
    }
    


}