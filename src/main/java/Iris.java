public class Iris {
    public static void main(String[] args) {
        String logo = ".___       .__        \n"
                + "|   |______|__| ______\n"
                + "|   \_  __ \  |/  ___/\n"
                + "|   ||  | \/  |\___ \ \n"
                + "|___||__|  |__/____  >\n"
                + "                   \/ \n";
        //Welcome Message
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");


    private static void input(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("Input cannot be empty.");
            return;
        }
        else if (message == "bye") {
        //Exit Message
        System.out.println("Bye. Hope to see you again soon!");
            return;
        }
        else{
            echo(message);
            String response = scanner.nextLine();
            return input(response);
        }
    }

    private static void echo(String message) {
        System.out.println(message);
    }
}
