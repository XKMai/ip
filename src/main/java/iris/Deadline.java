package iris;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Represents a task with a deadline
public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by.format(fmt) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.toString();
    }
}
