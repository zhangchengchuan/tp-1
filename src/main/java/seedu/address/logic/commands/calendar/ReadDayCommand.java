package seedu.address.logic.commands.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ReadDayCommand extends Command {
    public static final String COMMAND_WORD = "readDay";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the list of tasks on a particular day identified by the index number used in the calendar.\n"
            + "Parameters: INDEX (must be a valid day-of-month)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String SHOWING_TASKS_IN_DAY = "Displayed the list of task(s) in %s";

    private final LocalDate date;

    public ReadDayCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        return new CalendarCommandResult(String.format(SHOWING_TASKS_IN_DAY, date), COMMAND_WORD + " " + date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadDayCommand // instanceof handles nulls
                && date.equals(((ReadDayCommand) other).date)); // state check
    }
}
