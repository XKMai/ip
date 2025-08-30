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
        this.filePath = filePath;
    }

    // Loads tasks from the file, returns an empty list if file doesn't exist
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // empty list
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                try {
                    Task t = TaskParser.parseTask(line);
                    tasks.add(t);
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
        }
        return tasks;
    }

    // Saves the list of tasks to the file
    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + System.lineSeparator());
        }
        writer.close();
    }
}
