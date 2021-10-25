package manageme.logic.parser.module;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageme.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static manageme.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static manageme.logic.commands.module.ModuleCommandTestUtil.MODNAME_DESC_A;
import static manageme.logic.commands.module.ModuleCommandTestUtil.MODNAME_DESC_B;
import static manageme.logic.commands.module.ModuleCommandTestUtil.VALID_MODNAME_A;
import static manageme.logic.commands.module.ModuleCommandTestUtil.VALID_MODNAME_B;
import static manageme.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageme.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import manageme.logic.commands.module.AddModuleCommand;
import manageme.model.module.ModuleName;

public class AddModuleCommandParserTest {

    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleName expectedModule = new ModuleName(VALID_MODNAME_B);

        // whitespace only preamble
<<<<<<< HEAD
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ModuleCommandTestUtil.NAME_DESC_MODULE_B
                + ModuleCommandTestUtil.LINK_DESC_MODULE_B, new AddModuleCommand(expectedModule.getModuleName()));
=======
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODNAME_DESC_B, new AddModuleCommand(expectedModule));
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_MODNAME_A, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODNAME_DESC_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
