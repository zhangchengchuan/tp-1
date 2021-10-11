package seedu.address.logic.parser.module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.commands.module.ModuleCommandTestUtil.LINK_DESC_MODULE_B;
import static seedu.address.logic.commands.module.ModuleCommandTestUtil.NAME_DESC_MODULE_B;
import static seedu.address.logic.commands.module.ModuleCommandTestUtil.VALID_LINK_MODULE_B;
import static seedu.address.logic.commands.module.ModuleCommandTestUtil.VALID_NAME_MODULE_B;
import static seedu.address.testutil.TypicalModules.MODULE_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandParserTest {

    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(MODULE_B).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MODULE_B + LINK_DESC_MODULE_B,
                new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MODULE_B + LINK_DESC_MODULE_B, expectedMessage);

        // missing link prefix
        assertParseFailure(parser, NAME_DESC_MODULE_B + VALID_LINK_MODULE_B ,expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MODULE_B + VALID_LINK_MODULE_B, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MODULE_B + LINK_DESC_MODULE_B,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
