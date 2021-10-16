package seedu.address.logic.commands.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.calendar.ReadDayCommand.COMMAND_WORD;
import static seedu.address.logic.commands.calendar.ReadDayCommand.SHOWING_TASKS_IN_DAY;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ReadDayCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_readDay_success() {
        LocalDate dummyDate = LocalDate.now();
        CalendarCommandResult calendarCommandResult = new CalendarCommandResult(
                String.format(SHOWING_TASKS_IN_DAY, dummyDate), COMMAND_WORD + " " + dummyDate);

        assertCommandSuccess(new ReadDayCommand(dummyDate), model, calendarCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ReadDayCommand readDayCommand = new ReadDayCommand(LocalDate.now());

        // same object -> return true
        assertTrue(readDayCommand.equals(readDayCommand));

        // same value -> return true
        assertTrue(readDayCommand.equals(new ReadDayCommand(LocalDate.now())));

        // null -> return false
        assertFalse(readDayCommand.equals(null));

        // different type -> return false
        assertFalse(readDayCommand.equals(new NextMonthCommand()));

        // different value -> return false
        assertFalse(readDayCommand.equals(new ReadDayCommand(LocalDate.now().plusDays(1))));
    }
}
