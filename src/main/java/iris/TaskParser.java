package iris;

// Parses a line from the save file into a Task object
public class TaskParser {
    // Parses a line and returns the corresponding Task
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
            case "T":
                Todo todo = new Todo(parts[2]);
                if (isDone) todo.markDone();
                return todo;

            case "D":
                Deadline dl = new Deadline(parts[2], parts[3]);
                if (isDone) dl.markDone();
                return dl;

            case "E":
                Event ev = new Event(parts[2], parts[3], parts[4]);
                if (isDone) ev.markDone();
                return ev;

            default:
                throw new IllegalArgumentException("Unknown task type in file: " + type);
        }
    }
}
