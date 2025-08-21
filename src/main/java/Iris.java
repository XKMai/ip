import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Iris {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<>();

    // Static nested class
    private static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markDone() {
            isDone = true;
        }

        public void markUndone() {
            isDone = false;
        }

        @Override
        public String toString() {
            return (isDone ? "[X] " : "[ ] ") + description;
        }
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
            String response = scanner.nextLine();
            if (!input(response)) break;
        }
    }

    // return false if we should exit
    private static boolean input(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("Input cannot be empty.");
            return true;
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
                    echo("Please specify a task number to mark.");
                } else {
                    markTask(parts[1], true);
                }
                break;

            case "unmark":
                if (parts.length < 2) {
                    echo("Please specify a task number to unmark.");
                } else {
                    markTask(parts[1], false);
                }
                break;

            default:
                addTask(message);
                break;
        }
        return true;
    }

    private static void addTask(String task) {
        tasks.add(new Task(task));
        echo("Added: " + task);
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            echo("No tasks in the list.");
            return;
        }
        echo("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            echo((i + 1) + "." + tasks.get(i));
        }
    }

    private static void markTask(String indexStr, boolean done) {
        try {
            int index = Integer.parseInt(indexStr.trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                echo("Invalid task number.");
                return;
            }
            Task task = tasks.get(index);

            if (done) {
                task.markDone();
                echo("Nice! I've marked this task as done:");
                echo("  " + task);
            } else {
                task.markUndone();
                echo("OK, I've marked this task as not done yet:");
                echo("  " + task);
            }
        } catch (NumberFormatException e) {
            echo("Invalid number format.");
        }
    }

    private static void echo(String message) {
        System.out.println("    " + message);
    }
}
