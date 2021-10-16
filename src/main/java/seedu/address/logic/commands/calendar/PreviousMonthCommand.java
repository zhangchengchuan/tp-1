package seedu.address.logic.commands.calendar;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class PreviousMonthCommand extends Command {
    public static final String COMMAND_WORD = "prevMonth";

    public static final String SHOWING_PREVIOUS_MONTH = "Changed calendar to previous month.";

    @Override
    public CommandResult execute(Model model) {
        return new CalendarCommandResult(SHOWING_PREVIOUS_MONTH, COMMAND_WORD);
    }
}
