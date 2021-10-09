package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


public class TaskTime {

    public final String value;
    public final Optional<LocalDateTime> time;

    /**
     * Constructs a {@code TaskTime}.
     *
     * @param taskTime A Task Time.
     */
    public TaskTime(String taskTime) {
        requireNonNull(taskTime);
        this.value = taskTime;
        this.time = Optional.of(LocalDateTime.parse(taskTime));
    }

    /**
     * Constructs an empty {@code TaskTime}.
     */
    public TaskTime() {
        this.value = "";
        this.time = Optional.empty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTime // instanceof handles nulls
                && value.equals(((TaskTime) other).value))
                && time.equals((((TaskTime) other).time)); // state check
    }

    public static TaskTime empty() {
        return new TaskTime();
    }

    public boolean isEmpty() {
        return time.isEmpty();
    }

    public LocalDateTime getTime() {
        if (time.isEmpty()) {
            return null;
        }
        return time.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, time);
    }
}
