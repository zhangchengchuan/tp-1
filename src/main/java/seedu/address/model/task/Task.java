package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in the Task List.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final Name name;
    private final Description description;
    private final Index index;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Description description, Index index) {
        requireAllNonNull(name, description, index);
        this.name = name;
        this.description = description;
        this.index = index;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Index getIndex() {
        return index;
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
                && otherTask.getIndex().equals(getIndex());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, index);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Index: ")
                .append(getIndex());
        return builder.toString();
    }
}
