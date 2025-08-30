public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() { isDone = true; }
    public void markUndone() { isDone = false; }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public abstract String toString();
    public abstract String toSaveFormat();
}
