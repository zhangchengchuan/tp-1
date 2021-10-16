package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.DESC_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.DESC_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_MODULE_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalManageMe.getTypicalManageMe;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.ManageMe;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTaskCommand.
 */
public class EditTaskCommandTest {
    private Model model = new ModelManager(getTypicalManageMe(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder().build();
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editCommand = new EditTaskCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ManageMe(model.getManageMe()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withName(VALID_NAME_B).withDescription(VALID_DESCRIPTION_B)
                .withModule(VALID_MODULE_B).build();

        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withName(VALID_NAME_B)
                .withDescription(VALID_DESCRIPTION_B).withModule(VALID_MODULE_B).build();
        EditTaskCommand editCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ManageMe(model.getManageMe()), new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTaskCommand editCommand = new EditTaskCommand(INDEX_FIRST,
                new EditTaskCommand.EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ManageMe(model.getManageMe()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withName(VALID_NAME_B).build();
        EditTaskCommand editCommand = new EditTaskCommand(INDEX_FIRST,
                new EditTaskDescriptorBuilder().withName(VALID_NAME_B).build());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ManageMe(model.getManageMe()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder(firstTask).build();
        EditTaskCommand editCommand = new EditTaskCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model, EditTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withName(VALID_NAME_B).build();
        EditTaskCommand editCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getManageMe().getTaskList().size());

        EditTaskCommand editCommand = new EditTaskCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withName(VALID_NAME_B).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST, DESC_A);

        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor copyDescriptor = new EditTaskCommand.EditTaskDescriptor(DESC_A);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND, DESC_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST, DESC_B)));
    }
}
