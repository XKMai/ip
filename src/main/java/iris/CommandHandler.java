package iris;

// Handles commands like mark, unmark, todo, deadline, event, delete
public class CommandHandler {
    // Mark a task as done or undone
    public static void mark(String[] parts, TaskList tasks, Ui ui, Storage storage, boolean done) throws IrisException {
        if (parts.length < 2) {
            throw new IrisException("Please specify a task number.");
        }
        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            Task task = tasks.get(index);
            if (done) {
                task.markDone();
                ui.showMessage("Nice! I've marked this task as done:\n  " + task);
            } else {
                task.markUndone();
                ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
            }
            storage.save(tasks.getAll());
        } catch (Exception e) {
            throw new IrisException("Invalid task number.");
        }
    }

    // Add a new task (todo, deadline, event)
    public static void addTask(String command, String[] parts, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        if (parts.length < 2) throw new IrisException("Empty description.");
        Task t;
        switch (command) {
            case "todo":
                t = new Todo(parts[1]);
                break;
            case "deadline":
                String[] dParts = parts[1].split("/by", 2);
                if (dParts.length < 2) throw new IrisException("Deadline must include /by <time>.");
                t = new Deadline(dParts[0].trim(), dParts[1].trim());
                break;
            case "event":
                String[] fromSplit = parts[1].split("/from", 2);
                if (fromSplit.length < 2) throw new IrisException("Event must include /from and /to.");
                String desc = fromSplit[0].trim();
                String[] toSplit = fromSplit[1].split("/to", 2);
                if (toSplit.length < 2) throw new IrisException("Event must include /to.");
                t = new Event(desc, toSplit[0].trim(), toSplit[1].trim());
                break;
            default:
                throw new IrisException("Unknown add command.");
        }

        tasks.add(t);
        try {
            storage.save(tasks.getAll());
        } catch (Exception e) {
            ui.showError("Error saving task.");
        }
        ui.showMessage("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks.");
    }

    // Delete a task
    public static void delete(String[] parts, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        if (parts.length < 2) throw new IrisException("Please specify a task number to delete.");
        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            Task removed = tasks.delete(index);
            ui.showMessage("Noted. I've removed this task:\n  " + removed +
                           "\nNow you have " + tasks.size() + " tasks.");
            storage.save(tasks.getAll());
        } catch (Exception e) {
            throw new IrisException("Invalid task number.");
        }
    }
}
