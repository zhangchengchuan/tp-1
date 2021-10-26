package manageme.model.link;

import static java.util.Objects.requireNonNull;
import static manageme.commons.util.AppUtil.checkArgument;

import java.util.Optional;

public class LinkTask {
    public static final String MESSAGE_CONSTRAINTS =
            "Tasks should only contain alphanumeric characters " + "and spaces, and it should not be blank";;
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;
    public final Optional<String> taskName;

    /**
     * Constructs a {@code LinkTask}.
     *
     * @param linkTask A Link Task.
     */
    public LinkTask(String linkTask) {
        requireNonNull(linkTask);
        checkArgument(isValidTask(linkTask), MESSAGE_CONSTRAINTS);
        this.value = linkTask;
        this.taskName = Optional.of(linkTask);
    }

    /**
     * Constructs an empty {@code TaskTask}.
     */
    public LinkTask() {
        this.value = "";
        this.taskName = Optional.empty();
    }

    /**
     * Returns true if a given string is a valid Task.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkTask // instanceof handles nulls
                && value.equals(((LinkTask) other).value)); // state check
    }

    public static LinkTask empty() {
        return new LinkTask();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
