package manageme.testutil;

import manageme.model.link.Link;
import manageme.model.module.Module;
import manageme.model.module.ModuleName;

public class ModuleBuilder {
    public static final String DEFAULT_NAME = "CS2103T";;

    private ModuleName name;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        name = new ModuleName(DEFAULT_NAME);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        name = moduleToCopy.getModuleName();
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = new ModuleName(name);
        return this;
    }

    public Module build() {
        return new Module(name);
    }
}
