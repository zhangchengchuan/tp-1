package manageme.ui.module;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import manageme.model.link.Link;
import manageme.model.module.Module;
import manageme.model.task.Task;
import manageme.ui.UiPart;
import manageme.ui.link.LinkCard;
import manageme.ui.task.TaskCard;

public class ModuleWindow extends UiPart<Stage> {
    private static final String FXML = "ModuleWindow.fxml";

    private Stage moduleWindow;

    private Module module;

    @FXML
    private Label name;
    @FXML
    private VBox linkList;
    @FXML
    private VBox taskList;

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
     * @param optionalModule the Optional that contains the specific module user requested to read.
     */
    public void display(Optional<Module> optionalModule, ObservableList<Link> unfilteredLinkList,
                        ObservableList<Task> unfilteredTaskList) {
        this.module = optionalModule.get();
        resetWindow();
        moduleWindow.setTitle(module.getModuleName().value);
        name.setText(module.getModuleName().value);
        fillLinkList(module, unfilteredLinkList);
        fillTaskList(module, unfilteredTaskList);
        moduleWindow.showAndWait();
    }

    /**
     * Resets Module Window.
     */
    private void resetWindow() {
        linkList.getChildren().clear();
        taskList.getChildren().clear();
    }

    /**
     * Fills up the task list.
     */
    public void fillTaskList(Module module, ObservableList<Task> unfilteredTaskList) {
        FilteredList<Task> tasks = unfilteredTaskList.filtered(task -> {
            Optional<String> taskModule = task.getTaskModule().moduleName;

            if (taskModule.isEmpty()) {
                return false;
            }

            return module.getModuleName().value.equals(taskModule.get());
        });

        for (int i = 0; i < tasks.size(); i++) {
            taskList.getChildren().add(new TaskCard(tasks.get(i), i + 1).getRoot());
        }
    }

    /**
     * Fills up the link list.
     */
    public void fillLinkList(Module module, ObservableList<Link> unfilteredLinkList) {
        FilteredList<Link> links = unfilteredLinkList.filtered(link -> {
            Optional<String> linkModule = link.getLinkModule().moduleName;

            if (linkModule.isEmpty()) {
                return false;
            }

            return module.getModuleName().value.equals(linkModule.get());
        });

        for (int i = 0; i < links.size(); i++) {
            linkList.getChildren().add(new LinkCard(links.get(i), i + 1).getRoot());
        }
    }
}
