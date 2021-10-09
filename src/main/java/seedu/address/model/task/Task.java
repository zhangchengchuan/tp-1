package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Represents a Task in the Task List.
 * Guarantees: details are present and not TaskTime.empty(), field values are validated, immutable.
 */
public class Task {
    private final TaskName name;
    private final TaskDescription description;

    //Optional: TaskModule that the task may be linked to. Can only be linked to max 1 module.
    private final TaskModule module;

    //Optional: Task may have an end DateTime(Deadline) or both a start and end DateTime(Event).
    private final TaskTime start;
    private final TaskTime end;

    /**
     * Basic Task with only name and description, other attributes are initialized to TaskTime.empty().
     * Every field must be present and not TaskTime.empty().
     */
    public Task(TaskName name, TaskDescription description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
        this.module = TaskModule.empty();
        this.start = TaskTime.empty();
        this.end = TaskTime.empty();
    }

    /**
     * Basic Task with an associated module.
     * Every field must be present and not TaskTime.empty().
     */
    public Task(TaskName name, TaskDescription description, TaskModule module) {
        requireAllNonNull(name, description, module);
        this.name = name;
        this.description = description;
        this.module = module;
        this.start = TaskTime.empty();
        this.end = TaskTime.empty();
    }

    /**
     * Task with an end date(Deadline). No modules.
     * Every field must be present and not TaskTime.empty().
     */
    public Task(TaskName name, TaskDescription description, TaskTime end) {
        requireAllNonNull(name, description, end);
        this.name = name;
        this.description = description;
        this.module = TaskModule.empty();
        this.start = TaskTime.empty();
        this.end = end;
    }

    /**
     * Task with an end date(Deadline). Includes module.
     * Every field must be present and not TaskTime.empty().
     */
    public Task(TaskName name, TaskDescription description, TaskModule module, TaskTime end) {
        requireAllNonNull(name, description, module, end);
        this.name = name;
        this.description = description;
        this.module = module;
        this.start = TaskTime.empty();
        this.end = end;
    }

    /**
     * Task with a start and end date(Event). No module.
     * Every field must be present and not TaskTime.empty().
     */
    public Task(TaskName name, TaskDescription description, TaskTime start, TaskTime end) {
        requireAllNonNull(name, description, start, end);
        this.name = name;
        this.description = description;
        this.module = TaskModule.empty();
        this.start = start;
        this.end = end;
    }

    /**
     * Task with a start and end date(Event). Includes module.
     * Every field must be present and not TaskTime.empty().
     */
    public Task(TaskName name, TaskDescription description, TaskModule module, TaskTime start,
                TaskTime end) {
        requireAllNonNull(name, description, module, start, end);
        this.name = name;
        this.description = description;
        this.module = module;
        this.start = start;
        this.end = end;
    }

    public TaskName getName() {
        return name;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public TaskModule getTaskModule() {
        return module;
    }

    public TaskTime getStart() {
        return start;
    }

    public TaskTime getEnd() {
        return end;
    }

    /**
     * Returns the dates that this {@code Task} object spans over.
     *
     * @return The sequential ordered stream of dates.
     */
    public Stream<LocalDate> getSpan() {
        if (!end.isEmpty()  && !start.isEmpty()) {
            return start.getTime().toLocalDate().datesUntil(end.time.get().plusDays(1).toLocalDate());
        } else if (start.isEmpty() && !end.isEmpty()) {
            return end.getTime().toLocalDate().datesUntil(end.getTime().plusDays(1).toLocalDate());
        } else {
            return null;
        }
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getTaskModule().equals(getTaskModule())
                && otherTask.getStart().equals(getStart())
                && otherTask.getEnd().equals(getEnd());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, module, start, end);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; TaskModule: ")
                .append(getTaskModule())
                .append("; Start Date/Time: ")
                .append(getTaskModule())
                .append("; End Date/Time: ")
                .append(getTaskModule());
        return builder.toString();
    }
}
