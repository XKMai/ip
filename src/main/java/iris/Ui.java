package iris;

import java.util.Scanner;

// Handles user interactions
public class Ui {
    private Scanner scanner;

    // Default constructor initializes the scanner
    public Ui() {
        scanner = new Scanner(System.in);
    }

    // Displays the welcome message with the provided logo
    public void showWelcome(String logo) {
        printLine();
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        printLine();
    }

    // Displays the exit message
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // Displays a line separator
    public void showLine() {
        printLine();
    }

    // Displays an error message
    public void showError(String message) {
        System.out.println("Error! " + message);
    }

    // Reads a command from the user
    public String readCommand() {
        return scanner.nextLine();
    }

    // Displays a general message
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    // Prints a line separator
    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}
