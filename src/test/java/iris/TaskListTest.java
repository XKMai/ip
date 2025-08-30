package iris;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void delete_validIndex_removesTask() {
        TaskList tasks = new TaskList();
        Task todo = new Todo("read book");
        tasks.add(todo);

        Task removed = tasks.delete(0);

        assertEquals(todo, removed);
        assertEquals(0, tasks.size());
    }

    @Test
    public void delete_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.delete(5);  // invalid index
        });
    }
}
