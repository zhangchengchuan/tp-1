package manageme.model.module;

import java.util.Objects;
import java.util.Optional;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import manageme.commons.util.CollectionUtil;
import manageme.model.link.Link;
import manageme.model.task.Task;

/**
 * Represents a Module in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

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
    public Module(ModuleName moduleName, ObservableList<Task> unfilteredTasks) {
        CollectionUtil.requireAllNonNull(moduleName);
        this.moduleName = moduleName;
        this.unfilteredTasks = unfilteredTasks;
        updateTasks();

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
    }

    /**
     * Updates the dependencies of module.
     *
     * @param newUnfilteredTasks the unfilteredTasks that module will listen to
     */
    public void updateDependencies(ObservableList<Task> newUnfilteredTasks) {
        this.unfilteredTasks = newUnfilteredTasks;

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

    public ModuleName getModuleName() {
        return moduleName;
    }

    public FilteredList<Link> getLink() {
        return links;
    }

    public FilteredList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns true if both mods have the same name.
     * This defines a weaker notion of equality between two mods.
     * @param otherMod
     */
    public boolean isSameModule(Module otherMod) {
        if (otherMod == this) {
            return true;
        }

        return otherMod != null
                && otherMod.getModuleName().equals(getModuleName());
    }

    /**
     * Returns true if both mods have the same identity and data fields.
     * This defines a stronger notion of equality between two mods.
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
        return otherMod.getModuleName().equals(getModuleName())
                && otherMod.getLink().equals(getLink());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleName())
                .append("; Link: ")
                .append(getLink())
                .append("; Tasks: ")
                .append(getTasks());

        return builder.toString();
    }
}
