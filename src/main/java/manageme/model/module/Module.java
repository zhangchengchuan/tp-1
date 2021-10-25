package manageme.model.module;

import manageme.commons.util.CollectionUtil;

/**
 * Represents a Module in the app.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Module {

    // Identity fields
    private final ModuleName moduleName;

    /**
     * Link optional
     */
    public Module(ModuleName moduleName) {
        CollectionUtil.requireAllNonNull(moduleName);
        this.moduleName = moduleName;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    /**
     * Returns true if both mods have the same name.
     */
    public boolean isSameModule(Module otherMod) {
        if (otherMod == this) {
            return true;
        }

        return otherMod != null
                && otherMod.getModuleName().equals(getModuleName());
    }

    /**
     * Returns true if both mods have the same identity.
     * This is the same as #isSameModule because of how Module is implemented,
     * two Modules will be equals as long as they have the same ModuleName.
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
        return otherMod.getModuleName().equals(getModuleName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return moduleName.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleName());

        return builder.toString();
    }
}
