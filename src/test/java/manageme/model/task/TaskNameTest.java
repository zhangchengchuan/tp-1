package manageme.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static manageme.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import manageme.testutil.Assert;
import manageme.testutil.TypicalTasks;

public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidTaskName_throwsIllegalArgumentException() {
        String invalidName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> TaskName.isValidName(null));

        // invalid name
        assertFalse(TaskName.isValidName("")); // empty string
        assertFalse(TaskName.isValidName(" ")); // spaces only
        assertFalse(TaskName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TaskName.isValidName("hello*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TaskName.isValidName("do work")); // alphabets only
        assertTrue(TaskName.isValidName("12345")); // numbers only
        assertTrue(TaskName.isValidName("sleep by 12pm")); // alphanumeric characters
        assertTrue(TaskName.isValidName("Work on CS2103T")); // with capital letters
        assertTrue(TaskName.isValidName("Plan out next week timetable by this Friday")); // long names
    }

    @Test
    public void equals() {
        // same name, returns true
        assertTrue(TypicalTasks.TASK_A.getName().equals(TypicalTasks.TASK_A.getName()));

    }
}
