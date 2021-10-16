package manageme.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import manageme.commons.core.GuiSettings;
import manageme.commons.core.LogsCenter;
import manageme.logic.commands.exceptions.CommandException;
import manageme.logic.parser.exceptions.ParseException;
import manageme.model.Model;
import manageme.model.task.Task;
import manageme.logic.commands.Command;
import manageme.logic.commands.CommandResult;
import manageme.logic.parser.ManageMeParser;
import manageme.model.ReadOnlyManageMe;
import manageme.model.module.Module;
import manageme.model.person.Person;
import manageme.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ManageMeParser manageMeParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        manageMeParser = new ManageMeParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = manageMeParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveManageMe(model.getManageMe());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }
    @Override
    public ReadOnlyManageMe getManageMe() {
        return model.getManageMe();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public ObservableList<Module> getReadModuleList() {
        return model.getReadModuleList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getUnfilteredTaskList() {
        return model.getUnfilteredTaskList();
    }

    @Override
    public Path getManageMeFilePath() {
        return model.getManageMeFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
