package manageme.logic.parser.module;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageme.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static manageme.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static manageme.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageme.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import manageme.logic.commands.module.AddModuleCommand;
import manageme.logic.commands.module.ModuleCommandTestUtil;
import manageme.model.module.Module;
import manageme.testutil.ModuleBuilder;
import manageme.testutil.TypicalModules;

public class AddModuleCommandParserTest {

    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(TypicalModules.MODULE_B).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + ModuleCommandTestUtil.NAME_DESC_MODULE_B +
                        ModuleCommandTestUtil.LINK_DESC_MODULE_B,
                new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                ModuleCommandTestUtil.VALID_NAME_MODULE_B + ModuleCommandTestUtil.LINK_DESC_MODULE_B,
                expectedMessage);

        // missing link prefix
        assertParseFailure(parser,
                ModuleCommandTestUtil.NAME_DESC_MODULE_B + ModuleCommandTestUtil.VALID_LINK_MODULE_B,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                ModuleCommandTestUtil.VALID_NAME_MODULE_B + ModuleCommandTestUtil.VALID_LINK_MODULE_B,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + ModuleCommandTestUtil.NAME_DESC_MODULE_B +
                        ModuleCommandTestUtil.LINK_DESC_MODULE_B,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
