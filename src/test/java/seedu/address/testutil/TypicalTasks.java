package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;

public class TypicalTasks {
    public static final Task TASK_A = new TaskBuilder().withName("Eat with friends")
            .withDescription("At NUS UTown")
            .withStartDateTime("2021-10-05T11:50")
            .withEndDateTime("2021-10-05T14:00").build();
    public static final Task TASK_B = new TaskBuilder().withName("Work on CS2100 Assignment 2")
            .withDescription("Refer to lectures 7-13")
            .withStartDateTime("2021-10-07T23:59")
            .withEndDateTime("2021-10-14T23:59").build();

    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_A, TASK_B));
    }
}
