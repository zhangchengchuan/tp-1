package manageme.model.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageme.testutil.Assert;
import manageme.testutil.TypicalTasks;


public class TaskDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    //    @Test
    //    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
    //        String invalidDescription = "";
    //        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    //    }

    @Test
    public void equals() {
        // same description, returns true
        assertTrue(TypicalTasks.TASK_A.getDescription().equals(TypicalTasks.TASK_A.getDescription()));

    }
}
