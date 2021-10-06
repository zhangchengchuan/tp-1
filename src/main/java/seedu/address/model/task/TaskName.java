package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TaskName {

    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters"
            + "and" + " spaces, and it should not be blank";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * Reference on REGEX: https://www.regular-expressions.info/quickstart.html
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String taskName;

    /**
     * Constructs a {@code Name}.
     *
     * @param taskName A valid Task name.
     */
    public TaskName(String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidName(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = taskName;
    }

    /**
     * Checks if name has a whitespace in front.
     * @param test the name to test/check
     * @return name check boolean
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskName // instanceof handles nulls
                && taskName.equals(((TaskName) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }

}
