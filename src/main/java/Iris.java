public class Iris {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Iris(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading tasks.");
            tasks = new TaskList();
        }
    }

    public void run() {
        String logo = ".___       .__        \n"
                + "|   |______|__| ______\n"
                + "|   \\_  __ \\  |/  ___/\n"
                + "|   ||  | \\/  |\\___ \\ \n"
                + "|___||__|  |__/____  >\n"
                + "                   \\/ \n";
        ui.showWelcome(logo);

        boolean isRunning = true;
        while (isRunning) {
            try {
                String command = ui.readCommand();
                isRunning = Parser.parse(command, tasks, ui, storage);
            } catch (IrisException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Iris("data/iris.txt").run();
    }
}
