package manageme.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static manageme.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageme.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageme.testutil.TypicalManageMe.getTypicalManageMe;

import org.junit.jupiter.api.Test;

import manageme.testutil.TypicalIndexes;
import manageme.commons.core.Messages;
import manageme.commons.core.index.Index;
import manageme.logic.commands.CommandResult;
import manageme.model.Model;
import manageme.model.ModelManager;
import manageme.model.UserPrefs;
import manageme.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ReadModuleCommand}.
 */
public class ReadModuleCommandTest {

    private Model model = new ModelManager(getTypicalManageMe(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToRead = model.getReadModuleList().get(TypicalIndexes.INDEX_FIRST.getZeroBased());
        ReadModuleCommand readModuleCommand = new ReadModuleCommand(TypicalIndexes.INDEX_FIRST);

        CommandResult expectedCommandResult = new CommandResult(readModuleCommand.MESSAGE_SUCCESS, false, false, true);

        ModelManager expectedModel = new ModelManager(model.getManageMe(), new UserPrefs());
        expectedModel.updateReadModuleList(module -> module.equals(moduleToRead));

        assertCommandSuccess(readModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ReadModuleCommand readModuleCommand = new ReadModuleCommand(outOfBoundIndex);

        assertCommandFailure(readModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ReadModuleCommand readModuleFirstCommand = new ReadModuleCommand(TypicalIndexes.INDEX_FIRST);
        ReadModuleCommand readModuleSecondCommand = new ReadModuleCommand(TypicalIndexes.INDEX_SECOND);

        // same object -> returns true
        assertTrue(readModuleFirstCommand.equals(readModuleFirstCommand));

        // same values -> returns true
        ReadModuleCommand deleteFirstCommandCopy = new ReadModuleCommand(TypicalIndexes.INDEX_FIRST);
        assertTrue(readModuleFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(readModuleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(readModuleFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(readModuleFirstCommand.equals(readModuleSecondCommand));
    }
}
