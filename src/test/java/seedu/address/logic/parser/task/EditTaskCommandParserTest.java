package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.DESCRIPTION_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.DESCRIPTION_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.END_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.MODULE_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.MODULE_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.NAME_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.NAME_DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.START_DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_END_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_MODULE_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_MODULE_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_NAME_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_START_A;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.EditTaskDescriptorBuilder;


public class EditTaskCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, TaskName.MESSAGE_CONSTRAINTS); // invalid name


        // valid name followed by invalid name. The test case for invalid name followed by valid name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + NAME_DESC_B + INVALID_NAME_DESC, TaskName.MESSAGE_CONSTRAINTS);
        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + DESCRIPTION_DESC_A + MODULE_DESC_A,
                TaskName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_A
                + MODULE_DESC_A + NAME_DESC_B + START_DESC_A + END_DESC_A;

        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withName(VALID_NAME_B)
                .withDescription(VALID_DESCRIPTION_A).withModule(VALID_MODULE_A).withStartDateTime(VALID_START_A)
                .withEndDateTime(VALID_END_A).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_B + NAME_DESC_A;

        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_B)
                .withName(VALID_NAME_A).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_A;
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withName(VALID_NAME_A).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_A;
        descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_A).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module
        userInput = targetIndex.getOneBased() + MODULE_DESC_A;
        descriptor = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_A).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start
        userInput = targetIndex.getOneBased() + START_DESC_A;
        descriptor = new EditTaskDescriptorBuilder().withStartDateTime(VALID_START_A).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end
        userInput = targetIndex.getOneBased() + END_DESC_A;
        descriptor = new EditTaskDescriptorBuilder().withEndDateTime(VALID_END_A).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NAME_DESC_A + DESCRIPTION_DESC_A + MODULE_DESC_A
                + START_DESC_A + END_DESC_A
                + DESCRIPTION_DESC_B + NAME_DESC_B + MODULE_DESC_B;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_B)
                .withDescription(VALID_DESCRIPTION_B).withModule(VALID_MODULE_B)
                .withStartDateTime(VALID_START_A).withEndDateTime(VALID_END_A).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_B;
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withName(VALID_NAME_B).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_B + INVALID_NAME_DESC + MODULE_DESC_B
                + NAME_DESC_B;
        descriptor =
                new EditTaskDescriptorBuilder().withName(VALID_NAME_B).withDescription(VALID_DESCRIPTION_B)
                .withModule(VALID_MODULE_B).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
