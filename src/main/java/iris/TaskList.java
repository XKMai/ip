package iris;

import java.util.ArrayList;
import java.util.List;

// Manages the list of tasks
public class TaskList {
    private List<Task> tasks;

    // Default constructor initializes an empty task list
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor to initialize with existing list of tasks
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Adds a task to the list
    public void add(Task t) {
        tasks.add(t);
    }

    // Deletes a task at the specified index and returns it
    public Task delete(int index) {
        return tasks.remove(index);
    }

    // Retrieves a task at the specified index
    public Task get(int index) {
        return tasks.get(index);
    }

    // Returns the number of tasks in the list
    public int size() {
        return tasks.size();
    }

    // Returns all tasks as a list
    public List<Task> getAll() {
        return tasks;
    }

    // Lists all tasks with their indices
    public void list(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("No tasks in the list.");
            return;
        }
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i));
        }
    }
}
