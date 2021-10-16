package manageme.model.task;

import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_B;
import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_END_B;
import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_MODULE_B;
import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_NAME_B;
import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_START_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageme.testutil.TaskBuilder;
import manageme.testutil.TypicalTasks;

public class TaskTest {
    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TypicalTasks.TASK_A.isSameTask(TypicalTasks.TASK_A));

        // null -> returns false
        assertFalse(TypicalTasks.TASK_A.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedA =
                new TaskBuilder(TypicalTasks.TASK_A).withDescription(VALID_DESCRIPTION_B).withModule(VALID_MODULE_B)
                        .withStartDateTime(VALID_START_B).withEndDateTime(VALID_END_B).build();
        assertTrue(TypicalTasks.TASK_A.isSameTask(editedA));

        // different name, all other attributes same -> returns false
        editedA = new TaskBuilder(TypicalTasks.TASK_A).withName(VALID_NAME_B).build();
        assertFalse(TypicalTasks.TASK_A.isSameTask(editedA));

        // name differs in case, all other attributes same -> returns false
        Task editedB = new TaskBuilder(TypicalTasks.TASK_B).withName(VALID_NAME_B.toLowerCase()).build();
        assertFalse(TypicalTasks.TASK_B.isSameTask(editedB));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_B + " ";
        editedB = new TaskBuilder(TypicalTasks.TASK_B).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalTasks.TASK_B.isSameTask(editedB));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task copyA = new TaskBuilder(TypicalTasks.TASK_A).build();
        assertTrue(TypicalTasks.TASK_A.equals(copyA));

        // same object -> returns true
        assertTrue(TypicalTasks.TASK_A.equals(TypicalTasks.TASK_A));

        // null -> returns false
        assertFalse(TypicalTasks.TASK_A.equals(null));

        // different type -> returns false
        assertFalse(TypicalTasks.TASK_A.equals(5));

        // different person -> returns false
        assertFalse(TypicalTasks.TASK_A.equals(TypicalTasks.TASK_B));

        // different name -> returns false
        Task editedA = new TaskBuilder(TypicalTasks.TASK_A).withName(VALID_NAME_B).build();
        assertFalse(TypicalTasks.TASK_A.equals(editedA));

        // different description -> returns false
        editedA = new TaskBuilder(TypicalTasks.TASK_A).withDescription(VALID_DESCRIPTION_B).build();
        assertFalse(TypicalTasks.TASK_A.equals(editedA));

        // different module -> returns false
        editedA = new TaskBuilder(TypicalTasks.TASK_A).withModule(VALID_MODULE_B).build();
        assertFalse(TypicalTasks.TASK_A.equals(editedA));

        // different start date/time -> returns false
        editedA = new TaskBuilder(TypicalTasks.TASK_A).withStartDateTime(VALID_START_B).build();
        assertFalse(TypicalTasks.TASK_A.equals(editedA));

        // different end date/time -> returns false
        editedA = new TaskBuilder(TypicalTasks.TASK_A).withEndDateTime(VALID_END_B).build();
        assertFalse(TypicalTasks.TASK_A.equals(editedA));
    }
}
