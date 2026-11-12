import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Text_Reader {
    private static final int MAX_JOB_PAGES = 10;
    private final String namesFile;
    private final String descriptionsFile;
    private final List<String> names;
    private final List<String> descriptions;
    private final Random random;

    public Text_Reader(String namesFile, String descriptionsFile)
        throws IOException {
        this.namesFile = namesFile;
        this.descriptionsFile = descriptionsFile;
        names = loadEntries(this.namesFile);
        descriptions = loadEntries(this.descriptionsFile);
        random = new Random();
    }

    public String getRandomName() {
        return names.get(random.nextInt(names.size()));
    }

    public String getRandomDescription() {
        return descriptions.get(random.nextInt(descriptions.size()));
    }

    public Print_Job createRandomJob() {
        int pages = random.nextInt(MAX_JOB_PAGES) + 1;
        return new Print_Job(pages, getRandomName(), getRandomDescription());
    }

    private List<String> loadEntries(String path) throws IOException {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("A text file path is required.");
        }

        List<String> entries = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(path),
            StandardCharsets.UTF_8)) {
            String entry = line.trim();
            if (!entry.isEmpty()) {
                entries.add(entry);
            }
        }
        if (entries.isEmpty()) {
            throw new IllegalArgumentException("No usable entries in " + path);
        }
        return entries;
    }
}