package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.module.Module;

public class Deadline extends Task {
    private final LocalDateTime endDateTime;

    /**
     * Constructor for a Deadline with module.
     */
    public Deadline (TaskName name, TaskDescription description, Module module,
                  LocalDateTime endDateTime) {
        super(name, description, module);
        requireAllNonNull(endDateTime);
        this.endDateTime = endDateTime;
    }

    /**
     * Constructor for a Deadline without module.
     */
    public Deadline (TaskName name, TaskDescription description, LocalDateTime endDateTime) {
        super(name, description);
        requireAllNonNull(endDateTime);
        this.endDateTime = endDateTime;
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return otherDeadline.getName().equals(getName())
                && otherDeadline.getDescription().equals(getDescription())
                && otherDeadline.getModule().equals(getModule())
                && otherDeadline.getEndDateTime().equals(getEndDateTime());
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getDescription(), getModule(), endDateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(super.toString());
        builder.append("; End Date/Time: ")
                .append(getEndDateTime());
        return builder.toString();
    }

}
