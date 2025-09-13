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
        assert ui != null : "Ui must not be null";
        assert tasks != null : "Task list must not be null";

        if (tasks.isEmpty()) {
            ui.showMessage("No tasks in the list.");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null : "Task at index " + i + " must not be null";
            sb.append(i + 1).append(". ").append(task).append("\n");
        }
        String output = sb.toString().trim();
        assert !output.isEmpty() : "List output must not be empty when tasks exist";

        ui.showMessage(output);
    }

    public void find(String keyword, Ui ui) {
        assert ui != null : "Ui must not be null";
        assert tasks != null : "Task list must not be null";
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword must not be null or empty";

        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            assert task != null : "Task in list must not be null";
            String desc = task.getDescription();
            assert desc != null : "Task description must not be null";

            if (desc.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            assert task != null : "Matching task at index " + i + " must not be null";
            sb.append(i + 1).append(". ").append(task).append("\n");
        }
        String output = sb.toString().trim();
        assert !output.isEmpty() : "Find output must not be empty when matches exist";

        ui.showMessage(output);
    }
}
