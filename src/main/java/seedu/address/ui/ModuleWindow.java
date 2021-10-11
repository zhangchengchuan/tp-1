package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

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
     * @param onlyModuleInList The filterModuleList that only contains the specific module that user requested to read.
     */
    public void display(List<Module> onlyModuleInList) {
        this.module = onlyModuleInList.get(0);
        resetWindow();
        moduleWindow.setTitle(module.getModuleName().value);
        name.setText(module.getModuleName().value);
        fillLinkList(module.getLink());
        //fillTaskList(module.getTask());
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
