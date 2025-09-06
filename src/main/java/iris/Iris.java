package iris;

public class Iris {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Iris() {
        storage = new Storage("data/iris.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading tasks.");
            tasks = new TaskList();
        }
    }

    /**
     * Returns Iris's response to user input.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, tasks, ui, storage);
        } catch (IrisException e) {
            return "Error: " + e.getMessage();
        }
    }
}
