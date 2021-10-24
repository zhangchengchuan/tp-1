package manageme.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static manageme.commons.util.CollectionUtil.requireAllNonNull;
import static manageme.logic.parser.CliSyntax.PREFIX_LINK;
import static manageme.logic.parser.CliSyntax.PREFIX_NAME;

import javafx.collections.ObservableList;
import manageme.logic.commands.Command;
import manageme.logic.commands.CommandResult;
import manageme.logic.commands.exceptions.CommandException;
import manageme.model.Model;
import manageme.model.module.Module;
import manageme.model.module.ModuleName;
import manageme.model.task.Task;

public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to ManageMe. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LINK + "LINK "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS1100 "
            + PREFIX_LINK + "https://www.google.com";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the app.";

    private final ModuleName moduleName;

    /**
     * Creates an AddModuleCommand to add the specified {@code Module}
     */
    public AddModuleCommand(ModuleName moduleName) {
        requireAllNonNull(moduleName);
        this.moduleName = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Task> unfilteredTasks = model.getUnfilteredTaskList();

        Module toAdd = new Module(moduleName, unfilteredTasks);

        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && moduleName.equals(((AddModuleCommand) other).moduleName));
    }
}
