package seedu.address.testutil;

import seedu.address.model.module.Module;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

/**
 * A utility class to help with building Task objects. (INCOMPLETE: DEFAULT MODULE IS NULL)
 */
public class TaskBuilder {
    public static final String DEFAULT_NAME = "Finish CS2103T";
    public static final String DEFAULT_DESCRIPTION = "This is a default template text to test "
            + "TaskDescription";
    public static final Module DEFAULT_MODULE = null;

    private TaskName name;
    private TaskDescription description;
    private Module module;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new TaskName(DEFAULT_NAME);
        description = new TaskDescription(DEFAULT_DESCRIPTION);
        module = DEFAULT_MODULE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        module = taskToCopy.getModule();
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

    public Task build() {
        return new Task(name, description, module);
    }

}
