package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.link.Link;

/**
 * Represents a Module in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModuleName moduleName;

    // Data fields
    private final Link link;

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleName moduleName, Link link) {
        requireAllNonNull(moduleName, link);
        this.moduleName = moduleName;
        this.link = link;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public Link getLink() {
        return link;
    }

    /**
     * Returns true if both mods have the same link.
     * This defines a weaker notion of equality between two mods.
     * @param otherMod
     */
    public boolean isSameModule(Module otherMod) {
        if (otherMod == this) {
            return true;
        }

        return otherMod != null
                && otherMod.getModuleName().equals(getModuleName());
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
        return otherMod.getModuleName().equals(getModuleName())
                && otherMod.getLink().equals(getLink());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, link);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleName())
                .append("; Link: ")
                .append(getLink());

        return builder.toString();
    }
}
