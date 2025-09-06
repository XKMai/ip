package iris;

import java.time.LocalDateTime;

public class TaskParser {
    public static Task parseTask(String line) throws IrisException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            Task todo = new Todo(description);
            if (isDone) todo.markDone();
            return todo;

        case "D":
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            Task deadline = new Deadline(description, by);
            if (isDone) deadline.markDone();
            return deadline;

        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3]);
            LocalDateTime to = LocalDateTime.parse(parts[4]);
            Task event = new Event(description, from, to);
            if (isDone) event.markDone();
            return event;

        default:
            throw new IrisException("Unknown task type in save file: " + type);
        }
    }
}
