package manageme.model.task;

import static manageme.testutil.Assert.assertThrows;
import static manageme.testutil.TypicalTasks.TASK_A;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTime(null));
    }
    @Test
    public void equals() {
        // same date/time, returns true
        assertTrue(TASK_A.getStart().equals(TASK_A.getStart()));
        assertTrue(TASK_A.getEnd().equals(TASK_A.getEnd()));
    }
    @Test
    public void empty() {
        // Empty TaskModule with value set as ""
        assertTrue(TaskTime.empty().value.equals(""));

        // Empty TaskModule with moduleName set as an empty Optional<String>
        assertTrue(TaskTime.empty().time.isEmpty());
    }
    @Test
    public void isEmpty() {
        // Empty TaskModule, returns true
        assertTrue(TaskTime.empty().isEmpty());

        // Empty TaskModule, returns false
        assertFalse(TASK_A.getStart().isEmpty());
    }

}
