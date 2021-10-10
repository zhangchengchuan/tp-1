package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskModule;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;

/**
 * A utility class to help with building Task objects. (INCOMPLETE: DEFAULT MODULE IS NULL)
 */
public class TaskBuilder {
    public static final String DEFAULT_NAME = "Finish CS2103T";
    public static final String DEFAULT_DESCRIPTION = "This is a default template text to test "
            + "TaskDescription";
    public static final TaskModule DEFAULT_MODULE = null;
    public static final TaskTime DEFAULT_STARTDATETIME = new TaskTime("2021-10-05T11:00");
    public static final TaskTime DEFAULT_ENDDATETIME = new TaskTime("2021-10-12T23:59");

    private TaskName name;
    private TaskDescription description;
    private TaskModule module;
    private TaskTime start;
    private TaskTime end;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new TaskName(DEFAULT_NAME);
        description = new TaskDescription(DEFAULT_DESCRIPTION);
        module = DEFAULT_MODULE;
        start = DEFAULT_STARTDATETIME;
        end = DEFAULT_ENDDATETIME;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        module = taskToCopy.getTaskModule();
        start = taskToCopy.getStart();
        end = taskToCopy.getEnd();
    }

    /**
     * Sets the {@code name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new TaskName(name);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code module} of the {@code Task} that we are building.
     */
    public TaskBuilder withModule(String taskModule) {
        this.module = new TaskModule(taskModule);
        return this;
    }
    /**
     * Parses the {@code start} into a {@code LocalDateTime} and set it to the {@code Task} that we are
     * building.
     */
    public TaskBuilder withStartDateTime(String start) {
        this.start = new TaskTime(start);
        return this;
    }

    /**
     * Parses the {@code end} into a {@code LocalDateTime} and set it to the {@code Task} that we are
     * building.
     */
    public TaskBuilder withEndDateTime(String end) {
        this.end = new TaskTime(end);
        return this;
    }

    public Task build() {
        return new Task(name, description, start, end);
    }

}

