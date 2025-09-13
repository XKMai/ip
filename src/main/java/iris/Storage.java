package iris;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Manages loading and saving tasks to a file
public class Storage {
    private String filePath;

    // Initializes storage with the given file path
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty";
        this.filePath = filePath;
    }

    // Loads tasks from the file, returns an empty list if file doesn't exist
    public List<Task> load() throws IOException {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty when loading";

        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // empty list
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                assert line != null : "Line read from file should not be null";

                try {
                    Task t = TaskParser.parseTask(line);
                    assert t != null : "Parsed task should not be null";
                    tasks.add(t);
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
        }

        assert tasks != null : "Task list returned by load() should not be null";
        return tasks;
    }

    // Saves the list of tasks to the file
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Task list to save must not be null";
        for (Task task : tasks) {
            assert task != null : "Task in task list must not be null before saving";
        }

        File file = new File(filePath);
        File dir = file.getParentFile();
        if (dir != null && !dir.exists()) {
            boolean created = dir.mkdirs();
            assert created || dir.exists() : "Directory should exist after mkdirs() call";
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                String saveFormat = task.toSaveFormat();
                assert saveFormat != null && !saveFormat.isEmpty() : "Task save format must not be null or empty";
                writer.write(saveFormat + System.lineSeparator());
            }
        }
    }
}
