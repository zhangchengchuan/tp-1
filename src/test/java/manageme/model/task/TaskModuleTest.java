package manageme.model.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageme.testutil.Assert;
import manageme.testutil.TypicalTasks;

public class TaskModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskModule(null));
    }

    //    @Test
    //    public void constructor_invalidTaskModule_throwsIllegalArgumentException() {
    //        String invalidModule = "";
    //        assertThrows(IllegalArgumentException.class, () -> new TaskModule(invalidModule));
    //    }
    @Test
    public void equals() {
        // same module, returns true
        assertTrue(TypicalTasks.TASK_A.getTaskModule().equals(TypicalTasks.TASK_A.getTaskModule()));
    }

    @Test
    public void empty() {
        // Empty TaskModule with value set as ""
        assertTrue(TaskModule.empty().value.equals(""));

        // Empty TaskModule with moduleName set as an empty Optional<String>
        assertTrue(TaskModule.empty().moduleName.isEmpty());
    }
}
