public class Print_Job {
    private final int pages;
    private final String requester;
    private final String description;

    public Print_Job(int pages, String requester, String description) {
        if (pages <= 0) {
            throw new IllegalArgumentException(
                "A job must have at least one page.");
        }
        if (requester == null || requester.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "The requester cannot be blank.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "The description cannot be blank.");
        }

        this.pages = pages;
        this.requester = requester;
        this.description = description;
    }


    public int getPages() {
        return pages;
    }


    public String getRequester() {
        return requester;
    }


    public String getDescription() {
        return description;
    }
}
