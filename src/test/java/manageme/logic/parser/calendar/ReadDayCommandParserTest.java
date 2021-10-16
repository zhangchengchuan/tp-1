package manageme.logic.parser.calendar;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import manageme.commons.core.Messages;
import manageme.logic.parser.CommandParserTestUtil;
import manageme.logic.commands.calendar.ReadDayCommand;

public class ReadDayCommandParserTest {
    private ReadDayCommandParser parser = new ReadDayCommandParser();

    @Test
    public void parse_validArgs_returnsReadDayCommand() {
        CommandParserTestUtil.assertParseSuccess(parser,
                LocalDate.now().toString(),
                new ReadDayCommand(LocalDate.now()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser,
                "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ReadDayCommand.MESSAGE_USAGE));
    }
}
