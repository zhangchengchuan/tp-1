package seedu.address.logic.parser.calendar;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.calendar.ReadDayCommand;

public class ReadDayCommandParserTest {
    private ReadDayCommandParser parser = new ReadDayCommandParser();

    @Test
    public void parse_validArgs_returnsReadDayCommand() {
        assertParseSuccess(parser, LocalDate.now().toString(), new ReadDayCommand(LocalDate.now()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadDayCommand.MESSAGE_USAGE));
    }
}
