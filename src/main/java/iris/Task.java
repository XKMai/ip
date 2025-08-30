package iris;

// Abstract class representing a general task
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Marks the task as done or not done
    public void markDone() { isDone = true; }
    public void markUndone() { isDone = false; }

    // Returns status icon for display
    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public abstract String toString();
    // Format for saving to file
    public abstract String toSaveFormat();
}
