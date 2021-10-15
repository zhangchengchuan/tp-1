package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalManageMe.getTypicalManageMe;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ReadModuleCommand}.
 */
public class ReadModuleCommandTest {

    private Model model = new ModelManager(getTypicalManageMe(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToRead = model.getReadModuleList().get(INDEX_FIRST.getZeroBased());
        ReadModuleCommand readModuleCommand = new ReadModuleCommand(INDEX_FIRST);

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
        ReadModuleCommand readModuleFirstCommand = new ReadModuleCommand(INDEX_FIRST);
        ReadModuleCommand readModuleSecondCommand = new ReadModuleCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(readModuleFirstCommand.equals(readModuleFirstCommand));

        // same values -> returns true
        ReadModuleCommand deleteFirstCommandCopy = new ReadModuleCommand(INDEX_FIRST);
        assertTrue(readModuleFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(readModuleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(readModuleFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(readModuleFirstCommand.equals(readModuleSecondCommand));
    }
}
