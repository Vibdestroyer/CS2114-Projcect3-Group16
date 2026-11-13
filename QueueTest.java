import student.TestCase;

public class QueueTest extends TestCase {
    private Queue queue;
    private Print_Job jobnum1;
    private Print_Job jobnum2;

    public void setUp() {
        queue = new Queue();
        jobnum1 = new Print_Job(4, "Madison", "Paper");
        jobnum2 = new Print_Job(5, "Chris", "Presentation");
    }


    public void testQueueEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());
        assertNull(queue.dequeue());
    }


    public void testDequeue() {
        Queue queue = new Queue();
        Print_Job jobnum1 = new Print_Job(2, "Madison", "Essay");
        Print_Job jobnum2 = new Print_Job(5, "Riley", "Notes");

        queue.enqueue(jobnum1);
        queue.enqueue(jobnum2);

        assertEquals(jobnum1, queue.dequeue());
        assertEquals(jobnum2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    public void testEnqueue() {
        queue.enqueue(jobnum1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals(jobnum1, queue.peek());

    }


    public void testEnqueueNull() {
        Exception exception = null;
        try {
            queue.enqueue(null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    public void testFirstInFirstOut() {
        queue.enqueue(jobnum1);
        queue.enqueue(jobnum2);
        assertEquals(jobnum1, queue.dequeue());
        assertEquals(jobnum2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    public void testPeekEmpty() {
        assertNull(queue.peek());
        assertEquals(0, queue.size());
    }


    public void testEmptyDequeue() {

        assertNull(queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    public void testSize() {
        assertEquals(0, queue.size());
        queue.enqueue(jobnum1);
        assertEquals(1, queue.size());
        queue.enqueue(jobnum2);
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
    }


    public void testPeekDoesNotRemove() {
        Queue queue = new Queue();
        Print_Job jobnum1 = new Print_Job(4, "Taylor", "Report");

        queue.enqueue(jobnum1);

        assertEquals(jobnum1, queue.peek());
        assertEquals(1, queue.size());
    }


    public void testAfterDequeue() {
        Queue queue = new Queue();
        Print_Job jobnum1 = new Print_Job(2, "Alex", "Notes");
        Print_Job jobnum2 = new Print_Job(3, "Sam", "Homework");

        queue.enqueue(jobnum1);
        assertEquals(jobnum1, queue.dequeue());
        assertTrue(queue.isEmpty());

        queue.enqueue(jobnum2);
        assertEquals(jobnum2, queue.peek());
        assertEquals(1, queue.size());
    }

}
