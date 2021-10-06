package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.module.Module;

/**
 * Represents a Task in the Task List.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskName name;
    private final TaskDescription description;
    private final Module module;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskDescription description, Module module) {
        requireAllNonNull(name, description, module);
        this.name = name;
        this.description = description;
        this.module = module;
    }

    /**
     * Module is not present and initialized to null.
     */
    public Task(TaskName name, TaskDescription description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
        this.module = null;
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
                && otherTask.getModule().equals(getModule());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, module);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Module: ")
                .append(getModule());
        return builder.toString();
    }
}
