package seedu.address.testutil;

import seedu.address.logic.commands.module.EditModuleCommand;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;


public class EditModuleDescriptorBuilder {
    private EditModuleCommand.EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleCommand.EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleCommand.EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleCommand.EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code person}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleCommand.EditModuleDescriptor();
        descriptor.setModuleName(module.getModuleName());
        descriptor.setLink(module.getLink());
    }

    /**
     * Sets the {@code ModuleName} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withName(String name) {
        descriptor.setModuleName(new ModuleName(name));
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withLink(String link) {
        descriptor.setLink(new Link(link));
        return this;
    }


    public EditModuleCommand.EditModuleDescriptor build() {
        return descriptor;
    }
}
