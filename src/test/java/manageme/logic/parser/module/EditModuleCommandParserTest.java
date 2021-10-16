package manageme.logic.parser.module;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageme.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static manageme.logic.commands.CommandTestUtil.LINK_DESC_GOOGLE;
import static manageme.logic.commands.CommandTestUtil.LINK_DESC_ZOOM;
import static manageme.logic.commands.CommandTestUtil.MODNAME_DESC_CS2100;
import static manageme.logic.commands.CommandTestUtil.MODNAME_DESC_CS2103;
import static manageme.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static manageme.logic.commands.CommandTestUtil.VALID_LINK_ZOOM;
import static manageme.logic.commands.CommandTestUtil.VALID_MODNAME_CS2100;
import static manageme.logic.commands.CommandTestUtil.VALID_MODNAME_CS2103;
import static manageme.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageme.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import manageme.testutil.EditModuleDescriptorBuilder;
import manageme.testutil.TypicalIndexes;
import manageme.commons.core.index.Index;
import manageme.logic.commands.module.EditModuleCommand;
import manageme.model.module.ModuleName;

public class EditModuleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleCommandParser parser = new EditModuleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MODNAME_CS2103, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditModuleCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + MODNAME_DESC_CS2103, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + MODNAME_DESC_CS2103, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, ModuleName.MESSAGE_CONSTRAINTS); // invalid name
        // TODO: Test Link inputs when its validation is implemented
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + MODNAME_DESC_CS2100 + LINK_DESC_ZOOM;

        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withName(VALID_MODNAME_CS2100).withLink(VALID_LINK_ZOOM).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_linkSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + LINK_DESC_ZOOM;

        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withLink(VALID_LINK_ZOOM).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_nameSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + MODNAME_DESC_CS2103;
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withName(VALID_MODNAME_CS2103).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + MODNAME_DESC_CS2100 + MODNAME_DESC_CS2103
                + LINK_DESC_ZOOM + LINK_DESC_GOOGLE;

        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withName(VALID_MODNAME_CS2103).withLink(VALID_LINK_GOOGLE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + MODNAME_DESC_CS2100;
        EditModuleCommand.EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder().withName(VALID_MODNAME_CS2100).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + MODNAME_DESC_CS2100 + LINK_DESC_ZOOM;
        descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODNAME_CS2100)
                .withLink(VALID_LINK_ZOOM).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
