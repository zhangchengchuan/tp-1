package seedu.address.testutil;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.module.EditModuleCommand;
import seedu.address.model.module.Module;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;

public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + module.getModuleName().value + " ");
        sb.append(PREFIX_LINK + module.getLink().link + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleCommand.EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.value).append(" "));
        descriptor.getLink().ifPresent(link -> sb.append(PREFIX_LINK).append(link.link).append(" "));
        return sb.toString();
    }
}
