package manageme.logic.parser;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageme.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static manageme.testutil.Assert.assertThrows;
import static manageme.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import manageme.logic.commands.ClearCommand;
import manageme.logic.commands.ExitCommand;
import manageme.logic.commands.HelpCommand;
import manageme.logic.commands.calendar.NextMonthCommand;
import manageme.logic.commands.calendar.PreviousMonthCommand;
import manageme.logic.commands.calendar.ReadDayCommand;
import manageme.logic.commands.module.AddModuleCommand;
import manageme.logic.commands.module.DeleteModuleCommand;
import manageme.logic.commands.module.EditModuleCommand;
import manageme.logic.commands.module.EditModuleCommand.EditModuleDescriptor;
import manageme.logic.commands.module.FindModuleCommand;
import manageme.logic.commands.module.ListModuleCommand;
import manageme.logic.parser.exceptions.ParseException;
import manageme.model.module.ModNameContainsKeywordsPredicate;
import manageme.model.module.Module;
import manageme.testutil.EditModuleDescriptorBuilder;
import manageme.testutil.ModuleBuilder;
import manageme.testutil.ModuleUtil;

public class ManageMeParserTest {

    private final ManageMeParser parser = new ManageMeParser();

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_addModule() throws Exception {
        Module module = new ModuleBuilder().build();
        AddModuleCommand command = (AddModuleCommand) parser.parseCommand(ModuleUtil.getAddModuleCommand(module));
        assertEquals(new AddModuleCommand(module.getModuleName()), command);
    }

    @Test
    public void parseCommand_deleteModule() throws Exception {
        DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(
                DeleteModuleCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteModuleCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editModule() throws Exception {
        Module module = new ModuleBuilder().build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();
        EditModuleCommand command = (EditModuleCommand) parser.parseCommand(EditModuleCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + ModuleUtil.getEditModuleDescriptorDetails(descriptor));
        assertEquals(new EditModuleCommand(INDEX_FIRST, descriptor), command);
    }


    @Test
    public void parseCommand_findModule() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindModuleCommand command = (FindModuleCommand) parser.parseCommand(
                FindModuleCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindModuleCommand(new ModNameContainsKeywordsPredicate(keywords)), command);
    }


    @Test
    public void parseCommand_listModule() throws Exception {
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD) instanceof ListModuleCommand);
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD + " 3") instanceof ListModuleCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_nextMonth() throws Exception {
        assertTrue(parser.parseCommand(NextMonthCommand.COMMAND_WORD) instanceof NextMonthCommand);
        assertTrue(parser.parseCommand(NextMonthCommand.COMMAND_WORD + " 3") instanceof NextMonthCommand);
    }

    @Test
    public void parseCommand_previousMonth() throws Exception {
        assertTrue(parser.parseCommand(PreviousMonthCommand.COMMAND_WORD) instanceof PreviousMonthCommand);
        assertTrue(parser.parseCommand(PreviousMonthCommand.COMMAND_WORD + " 3") instanceof PreviousMonthCommand);
    }

    @Test
    public void parseCommand_readDay() throws Exception {
        ReadDayCommand command = (ReadDayCommand) parser.parseCommand(
                ReadDayCommand.COMMAND_WORD + " " + LocalDate.now());
        assertEquals(new ReadDayCommand(LocalDate.now()), command);
    }
}
