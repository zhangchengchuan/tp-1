package manageme.model.module;

import java.util.Objects;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import manageme.commons.util.CollectionUtil;
import manageme.model.link.Link;
import manageme.model.task.Task;

/**
 * Represents a Module in the app.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Module {
    private static final ObservableList<Link> DEFAULT_LINK_LIST = FXCollections.emptyObservableList();
    private static final ObservableList<Task> DEFAULT_TASK_LIST = FXCollections.emptyObservableList();

    // Identity fields
    private final ModuleName moduleName;

    // Data fields
    private ObservableList<Link> unfilteredLinks;
    private FilteredList<Link> links;
    private ObservableList<Task> unfilteredTasks;
    private FilteredList<Task> tasks;

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleName moduleName, ObservableList<Link> unfilteredLinks, ObservableList<Task> unfilteredTasks) {
        CollectionUtil.requireAllNonNull(moduleName, unfilteredLinks, unfilteredTasks);
        this.moduleName = moduleName;
        this.unfilteredLinks = unfilteredLinks;
        this.unfilteredTasks = unfilteredTasks;
        updateLinks();
        updateTasks();

        unfilteredLinks.addListener((ListChangeListener<? super Link>) change -> {
            updateLinks();
        });
        unfilteredTasks.addListener((ListChangeListener<? super Task>) change -> {
            updateTasks();
        });
    }

    /**
     * Link optional
     */
    public Module(ModuleName moduleName) {
        CollectionUtil.requireAllNonNull(moduleName);
        this.moduleName = moduleName;
        this.unfilteredLinks = DEFAULT_LINK_LIST;
        this.unfilteredTasks = DEFAULT_TASK_LIST;
        updateLinks();
        updateTasks();
    }

    public boolean hasDependencies() {
        return !unfilteredLinks.isEmpty() && !unfilteredTasks.isEmpty();
    }

    /**
     * Sets the dependencies of module.
     */
    public void setDependencies(ObservableList<Link> unfilteredLinks, ObservableList<Task> unfilteredTasks) {
        this.unfilteredLinks = unfilteredLinks;
        this.unfilteredTasks = unfilteredTasks;
        updateLinks();
        updateTasks();

        unfilteredLinks.addListener((ListChangeListener<? super Link>) change -> {
            updateLinks();
        });
        unfilteredTasks.addListener((ListChangeListener<? super Task>) change -> {
            updateTasks();
        });
    }

    private void updateTasks() {
        tasks = unfilteredTasks.filtered(task -> {
            Optional<String> taskModule = task.getTaskModule().moduleName;

            if (taskModule.isEmpty()) {
                return false;
            }

            return moduleName.value.equals(taskModule.get());
        });
    }

    private void updateLinks() {
        links = unfilteredLinks.filtered(link -> {
            Optional<String> linkModule = link.getLinkModule().moduleName;

            if (linkModule.isEmpty()) {
                return false;
            }

            return moduleName.value.equals(linkModule.get());
        });
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public FilteredList<Link> getLinks() {
        return links;
    }

    public FilteredList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns true if both mods have the same name.
     */
    public boolean isSameModule(Module otherMod) {
        if (otherMod == this) {
            return true;
        }

        return otherMod != null
                && otherMod.getModuleName().equals(getModuleName());
    }

    /**
     * Returns true if both mods have the same identity.
     * This is the same as #isSameModule because of how Module is implemented,
     * two Modules will be equals as long as they have the same ModuleName.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherMod = (Module) other;
        return otherMod.getModuleName().equals(getModuleName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, unfilteredLinks, unfilteredLinks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleName())
                .append("; Links: ")
                .append(getLinks())
                .append("; Tasks: ")
                .append(getTasks());

        return builder.toString();
    }
}
