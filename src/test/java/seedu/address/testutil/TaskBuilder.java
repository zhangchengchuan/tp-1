<<<<<<< HEAD
package seedu.address.testutil;

import java.time.LocalDateTime;

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
    public static final LocalDateTime DEFAULT_STARTDATETIME = LocalDateTime.of(2021, 10, 6, 12, 30);
    public static final LocalDateTime DEFAULT_ENDDATETIME = LocalDateTime.of(2021, 10, 12, 23, 59);

    private TaskName name;
    private TaskDescription description;
    private Module module;
    private LocalDateTime start;
    private LocalDateTime end;

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
        module = taskToCopy.getModule();
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
     * Parses the {@code start} into a {@code LocalDateTime} and set it to the {@code Task} that we are
     * building.
     */
    public TaskBuilder withStartDateTime(String start) {
        this.start = LocalDateTime.parse(start);
        return this;
    }

    /**
     * Parses the {@code end} into a {@code LocalDateTime} and set it to the {@code Task} that we are
     * building.
     */
    public TaskBuilder withEndDateTime(String end) {
        this.end = LocalDateTime.parse(end);
        return this;
    }

    public Task build() {
        return new Task(name, description, start, end);
    }

}
=======
//package seedu.address.testutil;
//
//import seedu.address.model.module.Module;
//import seedu.address.model.task.Task;
//import seedu.address.model.task.TaskDescription;
//import seedu.address.model.task.TaskModule;
//import seedu.address.model.task.TaskName;
//
//import java.util.Optional;
//
///**
// * A utility class to help with building Task objects. (INCOMPLETE: DEFAULT MODULE IS NULL)
// */
//public class TaskBuilder {
//    public static final String DEFAULT_NAME = "Finish CS2103T";
//    public static final String DEFAULT_DESCRIPTION = "This is a default template text to test "
//            + "TaskDescription";
//    public static final Optional<TaskModule> DEFAULT_MODULE = Optional.empty();
//
//    private TaskName name;
//    private TaskDescription description;
//    private Optional<TaskModule> module;
//
//    /**
//     * Creates a {@code TaskBuilder} with the default details.
//     */
//    public TaskBuilder() {
//        name = new TaskName(DEFAULT_NAME);
//        description = new TaskDescription(DEFAULT_DESCRIPTION);
//        module = DEFAULT_MODULE;
//    }
//
//    /**
//     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
//     */
//    public TaskBuilder(Task taskToCopy) {
//        name = taskToCopy.getName();
//        description = taskToCopy.getDescription();
//        module = taskToCopy.getTaskModule();
//    }
//
//    /**
//     * Sets the {@code name} of the {@code Task} that we are building.
//     */
//    public TaskBuilder withName(String name) {
//        this.name = new TaskName(name);
//        return this;
//    }
//
//    /**
//     * Sets the {@code description} of the {@code Task} that we are building.
//     */
//    public TaskBuilder withDescription(String description) {
//        this.description = new TaskDescription(description);
//        return this;
//    }
//
//    public Task build() {
//        return new Task(name, description, module);
//    }
//
//}
>>>>>>> master
