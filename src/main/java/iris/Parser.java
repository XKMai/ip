package iris;

// Parses user input and executes corresponding commands
public class Parser {
    // Parses the input command and executes it
    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        if (input == null || input.isEmpty()) {
            throw new IrisException("Input cannot be empty.");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "bye":
                ui.showExit();
                return false;

            case "list":
                tasks.list(ui);
                break;

            case "mark":
                CommandHandler.mark(parts, tasks, ui, storage, true);
                break;

            case "unmark":
                CommandHandler.mark(parts, tasks, ui, storage, false);
                break;

            case "todo":
            case "deadline":
            case "event":
                CommandHandler.addTask(command, parts, tasks, ui, storage);
                break;

            case "delete":
                CommandHandler.delete(parts, tasks, ui, storage);
                break;

            default:
                throw new IrisException("Unknown command.");
        }
        return true;
    }
}
