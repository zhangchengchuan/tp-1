package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;


public class TaskTime {

    public static final String MESSAGE_CONSTRAINTS =
            "TaskTime can take any value, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final String DATETIME_PATTERN = "dd/MM/yyyy hh:mma";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
    private static final String NO_TIME = "";

    public final String value;
    public final Optional<LocalDateTime> time;


    /**
     * Constructs a {@code TaskTime}.
     *
     * @param taskTime A Task Time.
     */
    public TaskTime(String taskTime) {
        requireNonNull(taskTime);
        String trimmedT = taskTime.trim();
        this.time = Optional.of(LocalDateTime.parse(trimmedT));
        this.value = dateTimeFormatter.format(time.get());
    }

    /**
     * Constructs an empty {@code TaskTime}.
     */
    public TaskTime() {
        this.value = NO_TIME;
        this.time = Optional.empty();
    }

    public static boolean isValidTaskTime(String test) {
        return test.matches(VALIDATION_REGEX);
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
