package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.DESCRIPTION_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.DESCRIPTION_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.END_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.END_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.MODULE_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.MODULE_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.NAME_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.NAME_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.START_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.START_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK_A_MANUAL;
import static seedu.address.testutil.TypicalTasks.TASK_B_MANUAL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASK_B_MANUAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_B + DESCRIPTION_DESC_B + MODULE_DESC_B
                + START_DESC_B + END_DESC_B, new AddTaskCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_A + NAME_DESC_B + DESCRIPTION_DESC_B + MODULE_DESC_B
                + START_DESC_B + END_DESC_B, new AddTaskCommand(expectedTask));

        // multiple description - last description accepted
        assertParseSuccess(parser, NAME_DESC_B + DESCRIPTION_DESC_A + DESCRIPTION_DESC_B + MODULE_DESC_B
                + START_DESC_B + END_DESC_B, new AddTaskCommand(expectedTask));

        // multiple modules - last module accepted
        assertParseSuccess(parser, NAME_DESC_B + DESCRIPTION_DESC_B + MODULE_DESC_A + MODULE_DESC_B
                + START_DESC_B + END_DESC_B, new AddTaskCommand(expectedTask));

        // multiple start date/times - last date/time accepted
        assertParseSuccess(parser, NAME_DESC_B + DESCRIPTION_DESC_B + MODULE_DESC_B
                + START_DESC_A + START_DESC_B + END_DESC_B, new AddTaskCommand(expectedTask));

        // multiple end date/times - last date/time accepted
        assertParseSuccess(parser, NAME_DESC_B + DESCRIPTION_DESC_B + MODULE_DESC_B
                + START_DESC_B + END_DESC_A + END_DESC_B, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero modules
        Task expectedTask = new TaskBuilder(TASK_A_MANUAL).withModule("").build();
        assertParseSuccess(parser, NAME_DESC_A + DESCRIPTION_DESC_A + START_DESC_A + END_DESC_A,
                new AddTaskCommand(expectedTask));
    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_B + DESCRIPTION_DESC_B,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_B + VALID_DESCRIPTION_B,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_B + VALID_DESCRIPTION_B,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_B + MODULE_DESC_B + START_DESC_B
                + END_DESC_B , TaskName.MESSAGE_CONSTRAINTS);

        //Add more invalid testcases here once more are included

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_B + DESCRIPTION_DESC_B + MODULE_DESC_B
                        + START_DESC_B + END_DESC_B,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
