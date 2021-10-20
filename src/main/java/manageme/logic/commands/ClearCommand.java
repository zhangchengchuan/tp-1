package manageme.logic.commands;

import static java.util.Objects.requireNonNull;

import manageme.model.ManageMe;
import manageme.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setManageMe(new ManageMe());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
