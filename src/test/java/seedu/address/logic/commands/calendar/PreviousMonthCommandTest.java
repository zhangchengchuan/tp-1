package seedu.address.logic.commands.calendar;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.calendar.PreviousMonthCommand.COMMAND_WORD;
import static seedu.address.logic.commands.calendar.PreviousMonthCommand.SHOWING_PREVIOUS_MONTH;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class PreviousMonthCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_prevMonth_success() {
        CalendarCommandResult calendarCommandResult = new CalendarCommandResult(SHOWING_PREVIOUS_MONTH, COMMAND_WORD);
        assertCommandSuccess(new PreviousMonthCommand(), model, calendarCommandResult, expectedModel);
    }
}
