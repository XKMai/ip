package iris;

import java.time.LocalDateTime;

// Handles commands like mark, unmark, todo, deadline, event, delete
public class CommandHandler {
    // Mark a task as done or undone
    public static void mark(String[] parts, TaskList tasks, Ui ui, Storage storage, boolean done) throws IrisException {
        assert parts != null : "Parts array should not be null";
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (parts.length < 2) {
            throw new IrisException("Please specify a task number.");
        }
        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            assert index >= 0 && index < tasks.size() : "Index should be within task list bounds";

            Task task = tasks.get(index);
            assert task != null : "Retrieved task should not be null";

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
        assert command != null && !command.isEmpty() : "Command should not be null or empty";
        assert parts != null : "Parts array should not be null";
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (parts.length < 2) throw new IrisException("Empty description.");
        Task t;
        switch (command) {
            case "todo":
                assert parts[1] != null && !parts[1].trim().isEmpty() : "Todo description should not be empty";
                t = new Todo(parts[1].trim());
                break;

            case "deadline":
                String[] dParts = parts[1].split("/by", 2);
                if (dParts.length < 2) throw new IrisException("Deadline must include /by <date>.");
                LocalDateTime deadlineTime = DateTimeParser.parseDateTime(dParts[1].trim());
                assert deadlineTime != null : "Parsed deadline time should not be null";
                t = new Deadline(dParts[0].trim(), deadlineTime);
                break;

            case "event":
                String[] fromSplit = parts[1].split("/from", 2);
                if (fromSplit.length < 2) throw new IrisException("Event must include /from and /to.");
                String desc = fromSplit[0].trim();
                String[] toSplit = fromSplit[1].split("/to", 2);
                if (toSplit.length < 2) throw new IrisException("Event must include /to.");

                LocalDateTime from = DateTimeParser.parseDateTime(toSplit[0].trim());
                LocalDateTime to = DateTimeParser.parseDateTime(toSplit[1].trim());

                assert from != null : "Event start time should not be null";
                assert to != null : "Event end time should not be null";
                assert from.isBefore(to) : "Event start time must be before end time";

                t = new Event(desc, from, to);
                break;

            default:
                throw new IrisException("Unknown add command.");
        }

        tasks.add(t);
        assert tasks.size() > 0 : "Task list size should increase after adding a task";

        try {
            storage.save(tasks.getAll());
        } catch (Exception e) {
            ui.showError("Error saving task.");
        }
        ui.showMessage("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks.");
    }


    // Delete a task
    public static void delete(String[] parts, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        assert parts != null : "Parts array should not be null";
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (parts.length < 2) throw new IrisException("Please specify a task number to delete.");
        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            assert index >= 0 && index < tasks.size() : "Index should be within task list bounds";

            Task removed = tasks.delete(index);
            assert removed != null : "Deleted task should not be null";

            ui.showMessage("Noted. I've removed this task:\n  " + removed +
                           "\nNow you have " + tasks.size() + " tasks.");
            storage.save(tasks.getAll());
        } catch (Exception e) {
            throw new IrisException("Invalid task number.");
        }
    }

    // Add a new contact
    public static void addContact(String[] parts, ContactList contacts, Ui ui, ContactStorage storage) throws IrisException {
        if (parts.length < 2) {
            throw new IrisException("Please provide contact details in format: name, phone, email");
        }

        String[] details = parts[1].split(",", 3);
        if (details.length < 3) {
            throw new IrisException("Contact must have name, phone number, and email.");
        }

        Contact c = new Contact(details[0].trim(), details[1].trim(), details[2].trim());
        contacts.add(c);
        try {
            storage.save(contacts.getAll());
        } catch (Exception e) {
            ui.showError("Error saving contact.");
        }
        ui.showMessage("Got it. I've added this contact:\n  " + c +
                    "\nNow you have " + contacts.size() + " contacts.");
    }

    public static void deleteContact(String[] parts, ContactList contacts, Ui ui, ContactStorage storage) throws IrisException {
        if (parts.length < 2) {
            throw new IrisException("Please specify a contact number to delete.");
        }
        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            Contact removed = contacts.delete(index);
            try {
                storage.save(contacts.getAll());
            } catch (Exception e) {
                ui.showError("Error saving contact.");
            }
            ui.showMessage("Noted. I've removed this contact:\n  " + removed +
                        "\nNow you have " + contacts.size() + " contacts.");
        } catch (Exception e) {
            throw new IrisException("Invalid contact number.");
        }
        
    }

    public static void listContacts(ContactList contacts, Ui ui) {
        assert contacts != null : "ContactList should not be null";
        assert ui != null : "Ui should not be null";
        contacts.list(ui);
    }
}
