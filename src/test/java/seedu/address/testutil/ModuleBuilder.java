package seedu.address.testutil;

import seedu.address.model.link.Link;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.Module;

public class ModuleBuilder {
    public static final String DEFAULT_NAME = "CS2103T";
    public static final String DEFAULT_LINK = "https://nus-cs2103-ay2122s1.github.io/website/";

    private ModuleName name;
    private Link link;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        name = new ModuleName(DEFAULT_NAME);
        link = new Link(DEFAULT_LINK);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        name = moduleToCopy.getModuleName();
        link = moduleToCopy.getLink();
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Module} that we are building.
     */
    public ModuleBuilder withLink(String link) {
        this.link = new Link(link);
        return this;
    }

    public Module build() {
        return new Module(name, link);
    }
}
