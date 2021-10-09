package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.module.Module;

/**
 * Represents a Task in the Task List.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskName name;
    private final TaskDescription description;

    //Optional: Module that the task may be linked to. Can only be linked to max 1 module.
    private final Module module;

    //Optional: Task may have an end DateTime(Deadline) or both a start and end DateTime(Event).
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Basic Task with only name and description, other attributes are initialized to null.
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskDescription description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
        this.module = null;
        this.start = null;
        this.end = null;
    }

    /**
     * Basic Task with an associated module.
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskDescription description, Module module) {
        requireAllNonNull(name, description, module);
        this.name = name;
        this.description = description;
        this.module = module;
        this.start = null;
        this.end = null;
    }

    /**
     * Task with an end date(Deadline). No modules.
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskDescription description, LocalDateTime end) {
        requireAllNonNull(name, description, end);
        this.name = name;
        this.description = description;
        this.module = null;
        this.start = null;
        this.end = end;
    }

    /**
     * Task with an end date(Deadline). Includes module.
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskDescription description, Module module, LocalDateTime end) {
        requireAllNonNull(name, description, module, end);
        this.name = name;
        this.description = description;
        this.module = module;
        this.start = null;
        this.end = end;
    }

    /**
     * Task with a start and end date(Event). No module.
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskDescription description, LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(name, description, start, end);
        this.name = name;
        this.description = description;
        this.module = null;
        this.start = start;
        this.end = end;
    }

    /**
     * Task with a start and end date(Event). Includes module.
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskDescription description, Module module, LocalDateTime start,
                LocalDateTime end) {
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

    public Module getModule() {
        return module;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
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
                && otherTask.getModule().equals(getModule())
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
                .append("; Module: ")
                .append(getModule())
                .append("; Start Date/Time: ")
                .append(getModule())
                .append("; End Date/Time: ")
                .append(getModule());
        return builder.toString();
    }
}
