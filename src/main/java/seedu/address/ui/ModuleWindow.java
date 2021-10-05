package seedu.address.ui;

import java.util.List;

import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.model.module.Module;

public class ModuleWindow extends UiPart<Stage> {
    private static final String FXML = "ModuleWindow.fxml";

    private Stage moduleWindow;

    private Module module;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ModuleWindow(Stage root) {
        super(FXML, root);

        this.moduleWindow = root;
    }

    /**
     * Creates a new HelpWindow.
     */
    public ModuleWindow() {
        this(new Stage());
    }

    /**
     * Displays module details to the user.
     *
     * @param onlyModuleInList The filterModuleList that only contains the specific module that user requested to read.
     */
    public void display(List<Module> onlyModuleInList) {
        this.module = onlyModuleInList.get(0);
        moduleWindow.initModality(Modality.APPLICATION_MODAL);
        moduleWindow.setTitle(module.getModName().modName);
        moduleWindow.showAndWait();
    }
}
