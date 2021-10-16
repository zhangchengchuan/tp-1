package manageme.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static manageme.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import manageme.testutil.Assert;
import manageme.testutil.TypicalTasks;

public class TaskTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskTime(null));
    }
    @Test
    public void equals() {
        // same date/time, returns true
        assertTrue(TypicalTasks.TASK_A.getStart().equals(TypicalTasks.TASK_A.getStart()));
        assertTrue(TypicalTasks.TASK_A.getEnd().equals(TypicalTasks.TASK_A.getEnd()));
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
        assertFalse(TypicalTasks.TASK_A.getStart().isEmpty());
    }

}
