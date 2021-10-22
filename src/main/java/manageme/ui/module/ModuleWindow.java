package manageme.ui.module;

import java.util.List;
import java.util.Optional;

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
    public void display(Optional<Module> optionalModule) {
        this.module = optionalModule.get();
        resetWindow();
        moduleWindow.setTitle(module.getModuleName().value);
        name.setText(module.getModuleName().value);
        fillLinkList(module.getLink());
        fillTaskList(module.getTasks());
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
     *
     * @param tasks the Task objects.
     */
    public void fillTaskList(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            taskList.getChildren().add(new TaskCard(tasks.get(i), i).getRoot());
        }
    }

    /**
     * Fills up the link list.
     *
     * @param links the Link objects.
     */
    public void fillLinkList(List<Link> links) {
        for (int i = 0; i < links.size(); i++) {
            linkList.getChildren().add(new LinkCard(links.get(i), i).getRoot());
        }
    }

    /**
     * Fills up the link list.
     *
     * @param link the Link object.
     */
    public void fillLinkList(Link link) {
        linkList.getChildren().add(new LinkCard(link, 1).getRoot());
    }
}
