package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_A;

import org.junit.jupiter.api.Test;

public class TaskModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskModule(null));
    }
    @Test
    public void constructor_invalidTaskModule_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskModule(invalidModule));
    }
    @Test
    public void equals() {
        // same module, returns true
        assertTrue(TASK_A.getTaskModule().equals(TASK_A.getTaskModule()));
    }
    @Test
    public void empty() {
        // Empty TaskModule with value set as ""
        assertTrue(TaskModule.empty().value.equals(""));

        // Empty TaskModule with moduleName set as an empty Optional<String>
        assertTrue(TaskModule.empty().moduleName.isEmpty());
    }
}
