package manageme.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_B;
import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_END_B;
import static manageme.logic.commands.task.TaskCommandTestUtil.VALID_START_B;
import static manageme.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import manageme.testutil.Assert;
import manageme.testutil.TaskBuilder;
import manageme.testutil.TypicalTasks;
import manageme.model.task.exceptions.DuplicateTaskException;
import manageme.model.task.exceptions.TaskNotFoundException;

public class UniqueTaskListTest {
    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(TypicalTasks.TASK_A));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        assertTrue(uniqueTaskList.contains(TypicalTasks.TASK_A));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        Task editedA =
                new TaskBuilder(TypicalTasks.TASK_A).withDescription(VALID_DESCRIPTION_B)
                        .withStartDateTime(VALID_START_B)
                        .build();
        assertTrue(uniqueTaskList.contains(editedA));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        Assert.assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(TypicalTasks.TASK_A));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, TypicalTasks.TASK_A));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(TypicalTasks.TASK_A, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        Assert.assertThrows(TaskNotFoundException.class,
                () -> uniqueTaskList.setTask(TypicalTasks.TASK_A, TypicalTasks.TASK_A));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        uniqueTaskList.setTask(TypicalTasks.TASK_A, TypicalTasks.TASK_A);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(TypicalTasks.TASK_A);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        Task editedA =
                new TaskBuilder(TypicalTasks.TASK_A).withDescription(VALID_DESCRIPTION_B).withEndDateTime(VALID_END_B)
                        .build();
        uniqueTaskList.setTask(TypicalTasks.TASK_A, editedA);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedA);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        uniqueTaskList.setTask(TypicalTasks.TASK_A, TypicalTasks.TASK_B);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(TypicalTasks.TASK_B);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        uniqueTaskList.add(TypicalTasks.TASK_B);
        Assert.assertThrows(DuplicateTaskException.class,
                () -> uniqueTaskList.setTask(TypicalTasks.TASK_A, TypicalTasks.TASK_B));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        Assert.assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(TypicalTasks.TASK_A));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        uniqueTaskList.remove(TypicalTasks.TASK_A);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(TypicalTasks.TASK_B);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(TypicalTasks.TASK_A);
        List<Task> taskList = Collections.singletonList(TypicalTasks.TASK_B);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(TypicalTasks.TASK_B);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(TypicalTasks.TASK_A, TypicalTasks.TASK_A);
        Assert.assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
