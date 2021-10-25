package manageme.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static manageme.logic.commands.module.ModuleCommandTestUtil.VALID_MODNAME_A;
import static manageme.logic.commands.module.ModuleCommandTestUtil.VALID_MODNAME_B;
import static manageme.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import manageme.commons.core.GuiSettings;
import manageme.logic.commands.CommandResult;
import manageme.logic.commands.exceptions.CommandException;
import manageme.model.ManageMe;
import manageme.model.Model;
import manageme.model.ReadOnlyManageMe;
import manageme.model.ReadOnlyUserPrefs;
import manageme.model.link.Link;
import manageme.model.module.Module;
<<<<<<< HEAD
=======
import manageme.model.module.ModuleName;
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
import manageme.model.task.Task;
import manageme.testutil.ModuleBuilder;

public class AddModuleCommandTest {
    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        ModuleName validModuleName = new ModuleName(VALID_MODNAME_A);
        Module validModule = new ModuleBuilder().withName(VALID_MODNAME_A).build();

<<<<<<< HEAD
        CommandResult commandResult = new AddModuleCommand(validModule.getModuleName()).execute(modelStub);
=======
        CommandResult commandResult = new AddModuleCommand(validModuleName).execute(modelStub);
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f

        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS, validModule), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
<<<<<<< HEAD
        Module validModule = new ModuleBuilder().build();
        AddModuleCommand addCommand = new AddModuleCommand(validModule.getModuleName());
        AddModuleCommandTest.ModelStub modelStub = new AddModuleCommandTest.ModelStubWithModule(validModule);
=======
        ModuleName validModuleName = new ModuleName(VALID_MODNAME_A);
        Module validModule = new ModuleBuilder().withName(VALID_MODNAME_A).build();
        AddModuleCommand addCommand = new AddModuleCommand(validModuleName);
        ModelStub modelStub = new AddModuleCommandTest.ModelStubWithModule(validModule);
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f

        assertThrows(CommandException.class, AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
<<<<<<< HEAD
        Module cs110 = new ModuleBuilder().withName("CS110").build();
        Module cs220 = new ModuleBuilder().withName("CS220").build();
        AddModuleCommand addCs110Command = new AddModuleCommand(cs110.getModuleName());
        AddModuleCommand addCs220Command = new AddModuleCommand(cs220.getModuleName());
=======
        ModuleName cs2100 = new ModuleName(VALID_MODNAME_A);
        ModuleName cs2103 = new ModuleName(VALID_MODNAME_B);
        AddModuleCommand addCs2100Command = new AddModuleCommand(cs2100);
        AddModuleCommand addCs2103Command = new AddModuleCommand(cs2103);
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f

        // same object -> returns true
        assertTrue(addCs2100Command.equals(addCs2100Command));

        // same values -> returns true
<<<<<<< HEAD
        AddModuleCommand addCs110CommandCopy = new AddModuleCommand(cs110.getModuleName());
        assertTrue(addCs110Command.equals(addCs110CommandCopy));
=======
        AddModuleCommand addCs2100CommandCopy = new AddModuleCommand(cs2100);
        assertTrue(addCs2100Command.equals(addCs2100CommandCopy));
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f

        // different types -> returns false
        assertFalse(addCs2100Command.equals(1));

        // null -> returns false
        assertFalse(addCs2100Command.equals(null));

        // different module -> returns false
        assertFalse(addCs2100Command.equals(addCs2103Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getManageMeFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setManageMeFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
=======
        public void addLink(Link link) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
        public void setManageMe(ReadOnlyManageMe newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyManageMe getManageMe() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
        public boolean hasModule(Module module) {
=======
        public boolean hasLink(Link link) {
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
        public void deleteModule(Module target) {
=======
        public void deleteLink(Link target) {
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
        public void addModule(Module module) {
=======
        public void setLink(Link target, Link editedLink) {
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
        public void setModule(Module target, Module editedModule) {
=======
        public ObservableList<Link> getFilteredLinkList() {
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
        public Optional<Module> getReadModule() {
=======
        public void updateFilteredLinkList(Predicate<Link> predicate) {
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReadModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLink(Link link) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLink(Link link) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLink(Link link) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
        public void setLink(Link target, Link editedLink) {
=======
        public Optional<Module> getReadModule() {
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
            throw new AssertionError("This method should not be called.");
        }

        @Override
<<<<<<< HEAD
        public void openLink(Link link) {
=======
        public void setReadModule(Module module) {
>>>>>>> 785e076494ea707cccfc66ae26761543be13828f
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Link> getFilteredLinkList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLinkList(Predicate<Link> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getUnfilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Link> getUnfilteredLinkList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends AddModuleCommandTest.ModelStub {
        private final Module module;

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }
    }

    /**
     * A Model stub that always accept the module being added.
     */
    private class ModelStubAcceptingModuleAdded extends AddModuleCommandTest.ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public ReadOnlyManageMe getManageMe() {
            return new ManageMe();
        }
    }
}
