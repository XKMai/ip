package iris;

// Parses user input and executes corresponding commands
public class Parser {
    public static String parse(String input, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        if (input == null || input.isEmpty()) {
            throw new IrisException("Input cannot be empty.");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "bye":
            ui.showExit();
            return ui.getLastMessage();

        case "list":
            tasks.list(ui);
            return ui.getLastMessage();

        case "mark":
            CommandHandler.mark(parts, tasks, ui, storage, true);
            return ui.getLastMessage();

        case "unmark":
            CommandHandler.mark(parts, tasks, ui, storage, false);
            return ui.getLastMessage();

        case "todo":
        case "deadline":
        case "event":
            CommandHandler.addTask(command, parts, tasks, ui, storage);
            return ui.getLastMessage();

        case "delete":
            CommandHandler.delete(parts, tasks, ui, storage);
            return ui.getLastMessage();

        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new IrisException("Please provide a keyword to search.");
            }
            tasks.find(parts[1].trim(), ui);
            return ui.getLastMessage();

        default:
            throw new IrisException("Unknown command.");
        }
    }
}
