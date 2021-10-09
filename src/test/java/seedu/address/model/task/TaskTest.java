package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_END_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_START_B;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {
    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK_A.isSameTask(TASK_A));

        // null -> returns false
        assertFalse(TASK_A.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedA = new TaskBuilder(TASK_A).withDescription(VALID_DESCRIPTION_B)
                .withStartDateTime(VALID_START_B).withEndDateTime(VALID_END_B).build();
        assertTrue(TASK_A.isSameTask(editedA));

        // different name, all other attributes same -> returns false
        editedA = new TaskBuilder(TASK_A).withName(VALID_NAME_B).build();
        assertFalse(TASK_A.isSameTask(editedA));

        // name differs in case, all other attributes same -> returns false
        Task editedB = new TaskBuilder(TASK_B).withName(VALID_NAME_B.toLowerCase()).build();
        assertFalse(TASK_B.isSameTask(editedB));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_B + " ";
        editedB = new TaskBuilder(TASK_B).withName(nameWithTrailingSpaces).build();
        assertFalse(TASK_B.isSameTask(editedB));
    }
    @Test
    public void equals() {
        Task copyA = new TaskBuilder(TASK_A).build();
        assertTrue(copyA.getModule().equals(TASK_A.getModule()));
//        // same values -> returns true
//        Task copyA = new TaskBuilder(TASK_A).build();
//        assertTrue(TASK_A.equals(copyA));
//
//        // same object -> returns true
//        assertTrue(TASK_A.equals(TASK_A));
//
//        // null -> returns false
//        assertFalse(TASK_A.equals(null));
//
//        // different type -> returns false
//        assertFalse(TASK_A.equals(5));
//
//        // different person -> returns false
//        assertFalse(TASK_A.equals(TASK_B));
//
//        // different name -> returns false
//        Task editedA = new TaskBuilder(TASK_A).withName(VALID_NAME_B).build();
//        assertFalse(TASK_A.equals(editedA));
//
//        // different description -> returns false
//        editedA = new TaskBuilder(TASK_A).withDescription(VALID_DESCRIPTION_B).build();
//        assertFalse(TASK_A.equals(editedA));
//
//        // different start date/time -> returns false
//        editedA = new TaskBuilder(TASK_A).withStartDateTime(VALID_START_B).build();
//        assertFalse(TASK_A.equals(editedA));
//
//        // different end date/time -> returns false
//        editedA = new TaskBuilder(TASK_A).withEndDateTime(VALID_END_B).build();
//        assertFalse(TASK_A.equals(editedA));
    }
}
