package iris;

// Handles user interactions
public class Ui {
    private String lastMessage = "";

    public String getLastMessage() {
        return lastMessage;
    }

    public void showWelcome(String logo) {
        lastMessage = "Hello! I'm\n" + logo + "\nWhat can I do for you?";
    }

    public void showExit() {
        lastMessage = "Bye. Hope to see you again soon!";
    }

    public void showError(String message) {
        lastMessage = "Error! " + message;
    }

    public void showMessage(String msg) {
        lastMessage = msg;
    }
}
