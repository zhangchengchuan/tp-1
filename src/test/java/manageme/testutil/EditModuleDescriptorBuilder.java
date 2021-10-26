package manageme.testutil;

import manageme.logic.commands.module.EditModuleCommand;
import manageme.model.module.Module;
import manageme.model.module.ModuleName;


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
        descriptor.setModuleName(module.getModuleName());;
    }

    /**
     * Sets the {@code ModuleName} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withName(String name) {
        descriptor.setModuleName(new ModuleName(name));
        return this;
    }

    public EditModuleCommand.EditModuleDescriptor build() {
        return descriptor;
    }
}
