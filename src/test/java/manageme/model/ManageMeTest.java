package manageme.model;

import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKADDRESS_A;
import static manageme.logic.commands.link.LinkCommandTestUtil.VALID_LINKMODULE_B;
import static manageme.testutil.TypicalLinks.LINK_A;
import static manageme.testutil.TypicalLinks.LINK_B;
import static manageme.testutil.TypicalModules.MODULE_A;
import static manageme.testutil.TypicalTasks.TASK_A;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manageme.logic.commands.task.TaskCommandTestUtil;
import manageme.model.link.Link;
import manageme.model.link.exceptions.DuplicateLinkException;
import manageme.model.module.Module;
import manageme.model.module.exceptions.DuplicateModuleException;
import manageme.model.task.Task;
import manageme.model.task.exceptions.DuplicateTaskException;
import manageme.testutil.Assert;
import manageme.testutil.LinkBuilder;
import manageme.testutil.ModuleBuilder;
import manageme.testutil.TaskBuilder;
import manageme.testutil.TypicalManageMe;

public class ManageMeTest {

    private final ManageMe manageMe = new ManageMe();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), manageMe.getLinkList());
        assertEquals(Collections.emptyList(), manageMe.getTaskList());
        assertEquals(Collections.emptyList(), manageMe.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> manageMe.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ManageMe newData = TypicalManageMe.getTypicalManageMe();
        manageMe.resetData(newData);
        assertEquals(newData, manageMe);
    }

    @Test
    public void resetData_withDuplicateLinks_throwsDuplicateLinkException() {
        // Two links with the same identity fields
        Link editedAlice = new LinkBuilder(LINK_A).build();
        List<Link> newLinks = Arrays.asList(LINK_A, editedAlice);
        ManageMeLinkStub newData = new ManageMeLinkStub(newLinks);

        Assert.assertThrows(DuplicateLinkException.class, () -> manageMe.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedModuleA = new ModuleBuilder(MODULE_A).build();
        List<Module> newModules = Arrays.asList(MODULE_A, editedModuleA);
        ManageMeModuleStub newData = new ManageMeModuleStub(newModules);

        Assert.assertThrows(DuplicateModuleException.class, () -> manageMe.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedTaskA = new TaskBuilder(TASK_A).withDescription(TaskCommandTestUtil.VALID_DESCRIPTION_A)
                .build();
        List<Task> newTasks = Arrays.asList(TASK_A, editedTaskA);
        ManageMeTaskStub newData = new ManageMeTaskStub(newTasks);

        Assert.assertThrows(DuplicateTaskException.class, () -> manageMe.resetData(newData));
    }

    @Test
    public void hasLink_nullLink_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> manageMe.hasLink(null));
    }

    @Test
    public void hasLink_linkNotInAddressBook_returnsFalse() {
        assertFalse(manageMe.hasLink(LINK_A));
    }

    @Test
    public void hasLink_linkInAddressBook_returnsTrue() {
        manageMe.addLink(LINK_A);
        assertTrue(manageMe.hasLink(LINK_A));
    }

    @Test
    public void hasLink_linkWithSameIdentityFieldsInAddressBook_returnsTrue() {
        manageMe.addLink(LINK_A);
        Link editedAlice = new LinkBuilder(LINK_B).withAddress(VALID_LINKADDRESS_A).withModule(VALID_LINKMODULE_B)
                .build();
        assertTrue(manageMe.hasLink(editedAlice));
    }

    @Test
    public void getLinkList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> manageMe.getLinkList().remove(0));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> manageMe.hasModule(null));
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
        Module editedModule = new ModuleBuilder(MODULE_A).build();
        assertTrue(manageMe.hasModule(editedModule));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> manageMe.getModuleList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> manageMe.hasTask(null));
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
        Task editedTask = new TaskBuilder(TASK_A).withDescription(TaskCommandTestUtil.VALID_DESCRIPTION_A)
                .withModule(TaskCommandTestUtil.VALID_MODULE_A).build();
        assertTrue(manageMe.hasTask(editedTask));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> manageMe.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose links list can violate interface constraints.
     */
    private static class ManageMeLinkStub implements ReadOnlyManageMe {
        private final ObservableList<Link> links = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        private final ArrayList<Task> modifiableTasks = new ArrayList<>();

        ManageMeLinkStub(Collection<Link> links) {
            this.links.setAll(links);
        }

        @Override
        public ObservableList<Link> getLinkList() {
            return links;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ArrayList<Task> getModifiableTaskList() {
            return modifiableTasks;
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose Modules list can violate interface constraints.
     */
    private static class ManageMeModuleStub implements ReadOnlyManageMe {
        private final ObservableList<Link> links = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        private final ArrayList<Task> modifiableTasks = new ArrayList<>();

        ManageMeModuleStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Link> getLinkList() {
            return links;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ArrayList<Task> getModifiableTaskList() {
            return modifiableTasks;
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose Tasks list can violate interface constraints.
     */
    private static class ManageMeTaskStub implements ReadOnlyManageMe {
        private final ObservableList<Link> links = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        private final ArrayList<Task> modifiableTasks = new ArrayList<>();

        ManageMeTaskStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Link> getLinkList() {
            return links;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ArrayList<Task> getModifiableTaskList() {
            return modifiableTasks;
        }
    }

}
