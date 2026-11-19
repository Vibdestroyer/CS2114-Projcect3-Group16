public class Queue {
    private Node front;
    private Node rear;
    private int size;

    private static class Node {
        private final Print_Job job;
        private Node next;

        private Node(Print_Job job) {
            this.job = job;
            next = null;
        }
    }

    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }


    public void enqueue(Print_Job job) {
        if (job == null) {
            throw new IllegalArgumentException("A print job cannot be null.");
        }

        Node newNode = new Node(job);
        // Case 1: Queue is empty
        if (front == null) {
            front = newNode;
            rear = newNode;
        }

        //Case 2: New job has higher priority
        else if (job.getPriority()> front.job.getPriority())
        {
            newNode.next=front;
            front=newNode;
        }

        //case 3: Find the correct position
        else {

            Node current= front;

            while(current.next !=null
                && current.next.job.getPriority()>=job.getPriority())
                {
                    current=current.next;
                }
            newNode.next= current.next;
            current.next= newNode;

            if (newNode.next == null)
            {
                rear= newNode;
            }
        size++;
        }
}


    public Print_Job dequeue() {
        if (front == null) {
            return null;
        }

        Print_Job job = front.job;
        front = front.next;
        size--;
        if (front == null) {
            rear = null;
        }
        return job;
    }


    public Print_Job peek() {
        if (front == null) {
            return null;
        }
        return front.job;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }
}
