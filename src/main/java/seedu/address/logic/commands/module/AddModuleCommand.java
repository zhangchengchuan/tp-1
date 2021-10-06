package seedu.address.logic.commands.module;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.Module;

import static java.util.Objects.compare;
import static java.util.Objects.requireNonNull;

public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addMod";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";

    private final Module toAdd;

    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(toAdd)) {
            throw new DuplicateModuleException();
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}