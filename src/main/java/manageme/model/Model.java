package manageme.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import manageme.commons.core.GuiSettings;
import manageme.model.task.Task;
import manageme.model.module.Module;
import manageme.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' ManageMe file path.
     */
    Path getManageMeFilePath();

    /**
     * Sets the user prefs' ManageMe file path.
     */
    void setManageMeFilePath(Path manageMeFilePath);

    /**
     * Replaces ManageMe data with the data in {@code manageMe}.
     */
    void setManageMe(ReadOnlyManageMe manageMe);

    /** Returns the ManageMe */
    ReadOnlyManageMe getManageMe();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the ManageMe.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the ManageMe.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the ManageMe.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the ManageMe.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the ManageMe.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a module with the same identity as {@code module} exists in the ManageMe.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the ManageMe.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the ManageMe.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the ManageMe.
     * The module identity of {@code editedModule} must not be the same as another existing module in the ManageMe.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Returns an unmodifiable view of the read module list
     */
    ObservableList<Module> getReadModuleList();

    /**
     * Updates the filter of the read module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateReadModuleList(Predicate<Module> predicate);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the ManageMe.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the ManageMe.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the ManageMe.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedtask}.
     * {@code target} must exist in the ManageMe.
     * The task identity of {@code editedtask} must not be the same as another existing task in the ManageMe.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /** Returns an unmodifiable view of the unfiltered task list */
    ObservableList<Task> getUnfilteredTaskList();
}
