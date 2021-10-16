package manageme.model.task;

import static manageme.testutil.Assert.assertThrows;
import static manageme.testutil.TypicalTasks.TASK_A;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TaskDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    //    @Test
    //    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
    //        String invalidDescription = "";
    //        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    //    }

    @Test
    public void equals() {
        // same description, returns true
        assertTrue(TASK_A.getDescription().equals(TASK_A.getDescription()));

    }
}
