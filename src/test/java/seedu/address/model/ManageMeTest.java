package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_ZOOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.address.logic.commands.task.TaskCommandTestUtil.VALID_MODULE_A;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalManageMe.getTypicalManageMe;
import static seedu.address.testutil.TypicalModules.MODULE_A;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalTasks.TASK_A;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

public class ManageMeTest {

    private final ManageMe manageMe = new ManageMe();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), manageMe.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageMe.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ManageMe newData = getTypicalManageMe();
        manageMe.resetData(newData);
        assertEquals(newData, manageMe);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ManageMePersonStub newData = new ManageMePersonStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> manageMe.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedModuleA = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_ZOOM)
                .build();
        List<Module> newModules = Arrays.asList(MODULE_A, editedModuleA);
        ManageMeModuleStub newData = new ManageMeModuleStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> manageMe.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedTaskA = new TaskBuilder(TASK_A).withDescription(VALID_DESCRIPTION_A)
                .build();
        List<Task> newTasks = Arrays.asList(TASK_A, editedTaskA);
        ManageMeTaskStub newData = new ManageMeTaskStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> manageMe.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageMe.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(manageMe.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        manageMe.addPerson(ALICE);
        assertTrue(manageMe.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        manageMe.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(manageMe.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> manageMe.getPersonList().remove(0));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageMe.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(manageMe.hasModule(MODULE_A));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        manageMe.addModule(MODULE_A);
        assertTrue(manageMe.hasModule(MODULE_A));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        manageMe.addModule(MODULE_A);
        Module editedModule = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_ZOOM).build();
        assertTrue(manageMe.hasModule(editedModule));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> manageMe.getModuleList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageMe.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(manageMe.hasTask(TASK_A));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        manageMe.addTask(TASK_A);
        assertTrue(manageMe.hasTask(TASK_A));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInAddressBook_returnsTrue() {
        manageMe.addTask(TASK_A);
        Task editedTask = new TaskBuilder(TASK_A).withDescription(VALID_DESCRIPTION_A).withModule(VALID_MODULE_A)
                .build();
        assertTrue(manageMe.hasTask(editedTask));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> manageMe.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ManageMePersonStub implements ReadOnlyManageMe {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        ManageMePersonStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose Modules list can violate interface constraints.
     */
    private static class ManageMeModuleStub implements ReadOnlyManageMe {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        ManageMeModuleStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose Tasks list can violate interface constraints.
     */
    private static class ManageMeTaskStub implements ReadOnlyManageMe {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        ManageMeTaskStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
