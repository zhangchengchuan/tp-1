package seedu.address.logic.commands.calendar;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class NextMonthCommand extends Command {
    public static final String COMMAND_WORD = "nextMonth";

    public static final String SHOWING_NEXT_MONTH = "Changed calendar to next month.";

    @Override
    public CommandResult execute(Model model) {
        return new CalendarCommandResult(SHOWING_NEXT_MONTH, COMMAND_WORD);
    }
}
