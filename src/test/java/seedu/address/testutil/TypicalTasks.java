package seedu.address.testutil;

import seedu.address.model.task.Task;

public class TypicalTasks {
    public static final Task TASK_A = new TaskBuilder().withName("Eat with friends")
            .withDescription("At NUS UTown")
            .withModule("")
            .withStartDateTime("2021-10-05T11:50")
            .withEndDateTime("2021-10-05T14:00").build();
    public static final Task TASK_B = new TaskBuilder().withName("Work on CS2100 Assignment 2")
            .withDescription("Refer to lectures 7-13")
            .withModule("CS2100")
            .withStartDateTime("2021-10-07T23:59")
            .withEndDateTime("2021-10-14T23:59").build();

    private TypicalTasks() {} // prevents instantiation
}
