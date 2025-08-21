import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Iris {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<>();

    // Static nested class
    private static abstract class Task {
        protected String description; 
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markDone() { isDone = true; }
        public void markUndone() { isDone = false; }

        protected String getStatusIcon() {
            return isDone ? "[X]" : "[ ]";
        }

        public abstract String toString();
    }

    private static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + getStatusIcon() + " " + description;
        }
    }

    private static class Deadline extends Task {
        private String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + getStatusIcon() + " " + description + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        private String from;
        private String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
        }
    }

    // custom exception
    private static class IrisException extends Exception {
        public IrisException(String message) {
            super(message);
        }
    }


    public static void main(String[] args) {
        // Initializing
        String logo = ".___       .__        \n"
                + "|   |______|__| ______\n"
                + "|   \\_  __ \\  |/  ___/\n"
                + "|   ||  | \\/  |\\___ \\ \n"
                + "|___||__|  |__/____  >\n"
                + "                   \\/ \n";

        // Welcome Message
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String response = scanner.nextLine();
                if (!input(response)) break;
            } catch (IrisException e) {
                System.out.println("Error! " + e.getMessage());
            }
        }
    }

    // return false if we should exit
    private static boolean input(String message) throws IrisException {
        if (message == null || message.isEmpty()) {
            throw new IrisException("Input cannot be empty.");
        }

        String[] parts = message.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return false;

            case "list":
                listTasks();
                break;

            case "mark":
                if (parts.length < 2) {
                    throw new IrisException("Please specify a task number to mark.");
                }
                markTask(parts[1], true);
                break;

            case "unmark":
                if (parts.length < 2) {
                    throw new IrisException("Please specify a task number to unmark.");
                }
                markTask(parts[1], false);
                break;

            case "todo":
                if (parts.length < 2) {
                    throw new IrisException("Empty description for Todo task.");
                } else {
                    addTask(new Todo(parts[1]));
                }
                break;

            case "deadline":
                if (parts.length < 2) {
                    throw new IrisException("Empty description for Deadline task.");
                } else {
                    String[] deadlineParts = parts[1].split("/by", 2);
                    if (deadlineParts.length < 2) {
                        throw new IrisException("Deadline must include /by <time>.");
                    } else {
                        addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                    }
                }
                break;

            case "event":
                if (parts.length < 2) {
                    throw new IrisException("Empty description for Event task.");
                } else {
                    String[] fromSplit = parts[1].split("/from", 2);
                    if (fromSplit.length < 2) {
                        throw new IrisException("Event must include /from and /to.");
                    } else {
                        String desc = fromSplit[0].trim();
                        String[] toSplit = fromSplit[1].split("/to", 2);
                        if (toSplit.length < 2) {
                            throw new IrisException("Event must include /to.");
                        } else {
                            addTask(new Event(desc, toSplit[0].trim(), toSplit[1].trim()));
                        }
                    }
                }
                break;

            case "delete":
                if (parts.length < 2) {
                    throw new IrisException("Please specify a task number to delete.");
                } else {
                    deleteTask(parts[1]);
                }
                break;

            default:
                throw new IrisException("Unknown command.");
        }
        return true;
    }

        private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void markTask(String indexStr, boolean done) throws IrisException {
        try {
            int index = Integer.parseInt(indexStr.trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IrisException("Invalid task number.");
            }
            Task task = tasks.get(index);
            if (done) {
                task.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task);
            } else {
                task.markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + task);
            }
        } catch (NumberFormatException e) {
            throw new IrisException("Invalid number format for mark/unmark command.");
        }
    }


    private static void deleteTask(String indexStr) throws IrisException {
        try {
            int index = Integer.parseInt(indexStr.trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IrisException("Invalid task number.");
            }
            Task removed = tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removed);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new IrisException("Invalid number format for delete command.");
        }
    }
}
