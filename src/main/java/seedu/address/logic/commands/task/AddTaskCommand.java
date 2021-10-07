package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: "
            + PREFIX_NAME + "TASK_NAME "
            + PREFIX_DESCRIPTION + "TASK_DESCRIPTION "
            + PREFIX_STARTDATETIME + "TASK_START_DATETIME "
            + PREFIX_ENDDATETIME + "TASK_END_DATETIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Do homework "
            + PREFIX_DESCRIPTION + "Complete CS2103T quiz on testing. "
            + PREFIX_STARTDATETIME + "2021-10-05T11:50:55 "
            + PREFIX_ENDDATETIME + "2021-10-07T11:50:55";
    private final Task toAdd;
    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
