package iris;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Represents a task that occurs at a specific time period
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + getStatusIcon() + " " + description
                + " (from: " + from.format(fmt)
                + " to: " + to.format(fmt) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from.toString()
                + " | " + to.toString();
    }
}
