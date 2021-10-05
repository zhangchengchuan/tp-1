package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module's link in the app.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ModuleName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String modName;

    /**
     * Constructs a {@code Name}.
     *
     * @param modName A valid mod name.
     */
    public ModuleName(String modName) {
        requireNonNull(modName);
        checkArgument(isValidName(modName), MESSAGE_CONSTRAINTS);
        this.modName = modName;
    }

    /**
     * Returns true if a given string is a valid link.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return modName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleName // instanceof handles nulls
                && modName.equals(((ModuleName) other).modName)); // state check
    }

    @Override
    public int hashCode() {
        return modName.hashCode();
    }
}
