package seedu.address.model.link;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a link in the app.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS =
            "Link must be a valid website address";

    /*
     * The first character of the link must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // TODO: Link input validation
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String link;
    private String name;

    /**
     * Constructs a {@code Link}.
     *
     * @param link A valid link.
     */
    public Link(String link) {
        requireNonNull(link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        this.link = link;
    }

    public void addName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    public void updateName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid link.
     */
    public static boolean isValidLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        if (name != null) {
            return name + ": " + link;
        } else {
            return link;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && link.equals(((Link) other).link)); // state check
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }
}
