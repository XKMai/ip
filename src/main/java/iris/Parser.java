package iris;

// Parses user input and executes corresponding commands
public class Parser {
    public static String parse(String input, TaskList tasks, ContactList contacts, Ui ui, Storage storage, ContactStorage contactStorage) throws IrisException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (input == null || input.isEmpty()) {
            throw new IrisException("Input cannot be empty.");
        }

        String[] parts = input.split(" ", 2);
        assert parts.length >= 1 : "Input split must produce at least one part";

        String command = parts[0];
        assert command != null && !command.isEmpty() : "Command must not be null or empty";

        String result;

        switch (command) {
        case "bye":
            ui.showExit();
            result = ui.getLastMessage();
            break;

        case "list":
            tasks.list(ui);
            result = ui.getLastMessage();
            break;

        case "mark":
            CommandHandler.mark(parts, tasks, ui, storage, true);
            result = ui.getLastMessage();
            break;

        case "unmark":
            CommandHandler.mark(parts, tasks, ui, storage, false);
            result = ui.getLastMessage();
            break;

        case "todo":
        case "deadline":
        case "event":
            CommandHandler.addTask(command, parts, tasks, ui, storage);
            result = ui.getLastMessage();
            break;

        case "delete":
            CommandHandler.delete(parts, tasks, ui, storage);
            result = ui.getLastMessage();
            break;

        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new IrisException("Please provide a keyword to search.");
            }
            tasks.find(parts[1].trim(), ui);
            result = ui.getLastMessage();
            break;

        case "contact-add":
            CommandHandler.addContact(parts, contacts, ui, contactStorage);
            result = ui.getLastMessage();
            break;

        case "contact-delete":
            CommandHandler.deleteContact(parts, contacts, ui, contactStorage);
            result = ui.getLastMessage();
            break;

        case "contacts":
            CommandHandler.listContacts(contacts, ui);
            result = ui.getLastMessage();
            break;

        default:
            throw new IrisException("Unknown command.");
        }

        assert result != null : "Resulting message should not be null";
        assert !result.trim().isEmpty() : "Resulting message should not be empty";

        return result;
    }
}
