package manageme.logic.parser.link;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageme.logic.commands.link.LinkCommandTestUtil.INVALID_LINKADDRESS_DESC;
import static manageme.logic.commands.link.LinkCommandTestUtil.INVALID_LINKMODULE_DESC;
import static manageme.logic.commands.link.LinkCommandTestUtil.INVALID_LINKNAME_DESC;
import static manageme.logic.commands.link.LinkCommandTestUtil.LINKADDRESS_DESC_A;
import static manageme.logic.commands.link.LinkCommandTestUtil.LINKADDRESS_DESC_B;
import static manageme.logic.commands.link.LinkCommandTestUtil.LINKMODULE_DESC_A;
import static manageme.logic.commands.link.LinkCommandTestUtil.LINKMODULE_DESC_B;
import static manageme.logic.commands.link.LinkCommandTestUtil.LINKNAME_DESC_A;
import static manageme.logic.commands.link.LinkCommandTestUtil.LINKNAME_DESC_B;
import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKADDRESS_A;
import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKADDRESS_B;
import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKMODULE_A;
import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKMODULE_B;
import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKNAME_A;
import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKNAME_B;
import static manageme.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageme.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageme.testutil.TypicalIndexes.INDEX_FIRST;
import static manageme.testutil.TypicalIndexes.INDEX_SECOND;
import static manageme.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import manageme.commons.core.index.Index;
import manageme.logic.commands.link.EditLinkCommand;
import manageme.model.link.LinkAddress;
import manageme.model.link.LinkModule;
import manageme.model.link.LinkName;
import manageme.testutil.EditLinkDescriptorBuilder;

public class EditLinkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLinkCommand.MESSAGE_USAGE);

    private EditLinkCommandParser parser = new EditLinkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_LINKNAME_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditLinkCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + LINKNAME_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + LINKNAME_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_LINKNAME_DESC, LinkName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_LINKADDRESS_DESC, LinkAddress.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_LINKMODULE_DESC, LinkModule.MESSAGE_CONSTRAINTS);

        // invalid name followed by valid address
        assertParseFailure(parser, "1" + INVALID_LINKNAME_DESC + LINKADDRESS_DESC_A,
                LinkName.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + LINKNAME_DESC_A + INVALID_LINKNAME_DESC, LinkName.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_LINKNAME_DESC + INVALID_LINKADDRESS_DESC
                + INVALID_LINKMODULE_DESC, LinkName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + LINKNAME_DESC_A + LINKADDRESS_DESC_A + LINKMODULE_DESC_A;

        EditLinkCommand.EditLinkDescriptor descriptor = new EditLinkDescriptorBuilder().withName(VALID_LINKNAME_A)
                .withAddress(VALID_LINKADDRESS_A).withModule(VALID_LINKMODULE_A).build();
        EditLinkCommand expectedCommand = new EditLinkCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + LINKNAME_DESC_A + LINKADDRESS_DESC_A;

        EditLinkCommand.EditLinkDescriptor descriptor = new EditLinkDescriptorBuilder().withName(VALID_LINKNAME_A)
                .withAddress(VALID_LINKADDRESS_A).build();
        EditLinkCommand expectedCommand = new EditLinkCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + LINKNAME_DESC_A;
        EditLinkCommand.EditLinkDescriptor descriptor = new EditLinkDescriptorBuilder().withName(VALID_LINKNAME_A)
                .build();
        EditLinkCommand expectedCommand = new EditLinkCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + LINKADDRESS_DESC_A;
        descriptor = new EditLinkDescriptorBuilder().withAddress(VALID_LINKADDRESS_A).build();
        expectedCommand = new EditLinkCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module
        userInput = targetIndex.getOneBased() + LINKMODULE_DESC_A;
        descriptor = new EditLinkDescriptorBuilder().withModule(VALID_LINKMODULE_A).build();
        expectedCommand = new EditLinkCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + LINKNAME_DESC_A + LINKADDRESS_DESC_A + LINKMODULE_DESC_A
                + LINKNAME_DESC_B + LINKADDRESS_DESC_B + LINKMODULE_DESC_B;

        EditLinkCommand.EditLinkDescriptor descriptor = new EditLinkDescriptorBuilder().withName(VALID_LINKNAME_B)
                .withAddress(VALID_LINKADDRESS_B).withModule(VALID_LINKMODULE_B).build();
        EditLinkCommand expectedCommand = new EditLinkCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_LINKMODULE_DESC + LINKMODULE_DESC_A;
        EditLinkCommand.EditLinkDescriptor descriptor = new EditLinkDescriptorBuilder().withModule(VALID_LINKMODULE_A)
                .build();
        EditLinkCommand expectedCommand = new EditLinkCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + LINKNAME_DESC_A + INVALID_LINKADDRESS_DESC + LINKADDRESS_DESC_A;
        descriptor = new EditLinkDescriptorBuilder().withName(VALID_LINKNAME_A).withAddress(VALID_LINKADDRESS_A)
                .build();
        expectedCommand = new EditLinkCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
