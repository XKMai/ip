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
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        ui.showMessage(sb.toString().trim());
    }

    public void find(String keyword, Ui ui) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        ui.showMessage(sb.toString().trim());
    }

}
