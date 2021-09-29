package seedu.address.model.module;

import java.util.Objects;

import seedu.address.model.link.Link;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Module in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModName modName;

    // Data fields
    private final Link link;

    public Module(ModName modName, Link link) {
        requireAllNonNull(modName, link);
        this.modName = modName;
        this.link = link;
    }

    public ModName getModName() {
        return modName;
    }

    public Link getLink() {
        return link;
    }

    /**
     * Returns true if both mods have the same link.
     * This defines a weaker notion of equality between two mods.
     * @param otherMod
     */
    public boolean isSameMod(Module otherMod) {
        if (otherMod == this) {
            return true;
        }

        return otherMod != null
                && otherMod.getModName().equals(getModName());
    }

    /**
     * Returns true if both mods have the same identity and data fields.
     * This defines a stronger notion of equality between two mods.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherMod = (Module) other;
        return otherMod.getModName().equals(getModName())
                && otherMod.getLink().equals(getLink());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(modName, link);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModName())
                .append("; Link: ")
                .append(getLink());

        return builder.toString();
    }
}
