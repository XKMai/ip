import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome(String logo) {
        printLine();
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        printLine();
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        printLine();
    }

    public void showError(String message) {
        System.out.println("Error! " + message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}
