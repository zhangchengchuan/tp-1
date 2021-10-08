package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class TaskDescription {

    public final String value;

    /**
     * Constructs a {@code TaskDescription}.
     *
     * @param taskDescription A Task Description.
     */
    public TaskDescription(String taskDescription) {
        requireNonNull(taskDescription);
        this.value = taskDescription;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDescription // instanceof handles nulls
                && value.equals(((TaskDescription) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
