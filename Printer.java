public class Printer {
    private final int maxPaper;
    private int currentPaper;
    private boolean isJammed;
    private final Queue printQueue;

    public Printer(int maxPaper) {
        if (maxPaper <= 0) {
            throw new IllegalArgumentException(
                "Paper capacity must be positive.");
        }
        this.maxPaper = maxPaper;
        currentPaper = 0;
        isJammed = false;
        printQueue = new Queue();
    }


    public void addPaper(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Add at least one sheet of paper.");
        }
        if (amount > maxPaper - currentPaper) {
            throw new IllegalArgumentException("Only " + (maxPaper
                - currentPaper) + " more sheets will fit.");
        }
        currentPaper += amount;
    }


    public void addJob(Print_Job job) {
        printQueue.enqueue(job);
    }


    public boolean printNextJob() {
        Print_Job nextJob = printQueue.peek();
        if (isJammed || nextJob == null) {
            return false;
        }
        if (currentPaper < nextJob.getPages()) {
            return false;
        }

        currentPaper -= nextJob.getPages();
        printQueue.dequeue();
        return true;
    }


    public void jam() {
        isJammed = true;
    }


    public void clearJam() {
        isJammed = false;
    }


    public int getCurrentPaper() {
        return currentPaper;
    }


    public int getMaxPaper() {
        return maxPaper;
    }


    public boolean isJammed() {
        return isJammed;
    }


    public int getPendingJobs() {
        return printQueue.size();
    }


    public Print_Job getNextJob() {
        return printQueue.peek();
    }


    public boolean skipNextJob() {
        if (printQueue.isEmpty()) {
            return false;
        }
        Print_Job skipped = printQueue.dequeue();
        printQueue.enqueue(skipped);
        return true;
    }
}
