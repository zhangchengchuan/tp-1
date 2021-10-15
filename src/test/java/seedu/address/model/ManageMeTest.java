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
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
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

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookPersonStub newData = new AddressBookPersonStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedModuleA = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_ZOOM)
                .build();
        List<Module> newModules = Arrays.asList(MODULE_A, editedModuleA);
        AddressBookModuleStub newData = new AddressBookModuleStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedTaskA = new TaskBuilder(TASK_A).withDescription(VALID_DESCRIPTION_A)
                .build();
        List<Task> newTasks = Arrays.asList(TASK_A, editedTaskA);
        AddressBookTaskStub newData = new AddressBookTaskStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModule(MODULE_A));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        addressBook.addModule(MODULE_A);
        assertTrue(addressBook.hasModule(MODULE_A));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addModule(MODULE_A);
        Module editedModule = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_ZOOM).build();
        assertTrue(addressBook.hasModule(editedModule));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getModuleList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTask(TASK_A));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        addressBook.addTask(TASK_A);
        assertTrue(addressBook.hasTask(TASK_A));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTask(TASK_A);
        Task editedTask = new TaskBuilder(TASK_A).withDescription(VALID_DESCRIPTION_A).withModule(VALID_MODULE_A)
                .build();
        assertTrue(addressBook.hasTask(editedTask));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookPersonStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        AddressBookPersonStub(Collection<Person> persons) {
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
    private static class AddressBookModuleStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        AddressBookModuleStub(Collection<Module> modules) {
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
    private static class AddressBookTaskStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        AddressBookTaskStub(Collection<Task> tasks) {
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
