package manageme.logic;

import java.nio.file.Path;

import manageme.commons.core.GuiSettings;
import manageme.logic.commands.exceptions.CommandException;
import manageme.logic.parser.exceptions.ParseException;
import manageme.model.Model;
import manageme.model.task.Task;
import manageme.logic.commands.CommandResult;
import manageme.model.ReadOnlyManageMe;
import manageme.model.module.Module;
import manageme.model.person.Person;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see Model#getManageMe()
     */
    ReadOnlyManageMe getManageMe();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<Module> getFilteredModuleList();

    /** Returns an unmodifiable view of the filtered list of modules to be read in detail */
    ObservableList<Module> getReadModuleList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the unfiltered list of tasks */
    ObservableList<Task> getUnfilteredTaskList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getManageMeFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
