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
    // TODO: Link content input validation
    public static final String VALIDATION_REGEX = "[^\\s].*";

    // TODO: Implement name and link as two separate classes
    public final String link;
    private String name;

    /**
     * Constructs a {@code Link}.
     *
     * @param link A valid link.
     */
    public Link(String name, String link) {
        requireNonNull(name, link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        // TODO: Link name input validation when name is a separate class.
        this.link = link;
        this.name = name;
    }


    // TODO: Remove this constructor for v1.3 since name will be compulsory
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

    /**
     * Add a name to link
     *
     * @param name name of the link
     */
    public void addName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Change the name of link to the new name
     *
     * @param name the new name
     */
    public void updateName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
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

    /**
     * Returns true if both links have the same link content.
     * This defines a weaker notion of equality between two mods.
     * @param otherlink
     */
    public boolean isSameLink(Link otherlink) {
        if (otherlink == this) {
            return true;
        }

        return otherlink != null
                && otherlink.getLink().equals(getLink());
    }

    /**
     * Returns true if both mods have the same name and link content.
     * This defines a stronger notion of equality between two mods.
     * @param other
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && link.equals(((Link) other).link)); // state check
                // TODO: Compare link names in equal when proper link class is done
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }
}
