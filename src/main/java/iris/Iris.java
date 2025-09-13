package iris;

public class Iris {
    private TaskList tasks;
    private ContactList contacts;
    private Storage storage;
    private ContactStorage contactStorage;
    private Ui ui;

    public Iris() {
        this.storage = new Storage("data/iris.txt");
        this.contactStorage = new ContactStorage("data/contacts.txt");
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
            this.contacts = ContactList.fromStorage(contactStorage);
        } catch (Exception e) {
            ui.showError("Error loading tasks.");
            this.tasks = new TaskList();
            this.contacts = new ContactList();
        }
    }

    /**
     * Returns Iris's response to user input.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, tasks, contacts, ui, storage, contactStorage);
        } catch (IrisException e) {
            return "Error: " + e.getMessage();
        }
    }
}
