import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Iris {
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        //Initializing
        String logo = ".___       .__        \n"
                + "|   |______|__| ______\n"
                + "|   \\_  __ \\  |/  ___/\n"
                + "|   ||  | \\/  |\\___ \\ \n"
                + "|___||__|  |__/____  >\n"
                + "                   \\/ \n";
        

        //Welcome Message
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        String response = scanner.nextLine();
        input(response);
    }

    private static void input(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("Input cannot be empty.");
            return;
        }
        switch (message){
            case "bye":
                //Exit Message
                System.out.println("Bye. Hope to see you again soon!");
                return;
            case "list":
                listTasks();
                break;
            default:
                addTask(message);
                break;
        }
        String response = scanner.nextLine();
        input(response);
        return;
    }

    private static void addTask(String task) {
        tasks.add(task);
        echo("Added: " + task);
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            echo("No tasks in the list.");
            return;
        }
        echo("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            echo((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void echo(String message) {
        System.out.println(message);
    }
}