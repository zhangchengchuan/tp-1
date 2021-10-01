package seedu.address.logic.commands.module;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

public class ReadModuleCommand extends Command {
    public static final String COMMAND_WORD = "readMod";

    public static final String MESSAGE_SUCCESS = "Displayed module";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
