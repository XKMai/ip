import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeParser.parseDateTime(from);
        this.to = DateTimeParser.parseDateTime(to);
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
