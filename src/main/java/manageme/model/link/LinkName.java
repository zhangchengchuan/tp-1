package manageme.model.link;

import static java.util.Objects.requireNonNull;

import manageme.commons.util.AppUtil;

public class LinkName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the link name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code LinkName}.
     *
     * @param linkName A valid mod name.
     */
    public LinkName(String linkName) {
        requireNonNull(linkName);
        AppUtil.checkArgument(isValidName(linkName), MESSAGE_CONSTRAINTS);
        this.value = linkName;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkName // instanceof handles nulls
                && value.equals(((LinkName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
