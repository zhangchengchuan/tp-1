package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Optional;


public class TaskModule {

    public final String value;
    public final Optional<String> moduleName;

    /**
     * Constructs a {@code TaskModule}.
     *
     * @param taskModule A Task Module.
     */
    public TaskModule(String taskModule) {
        requireNonNull(taskModule);
        this.value = taskModule;
        this.moduleName = Optional.of(taskModule);
    }

    /**
     * Constructs an empty {@code TaskModule}.
     */
    public TaskModule() {
        this.value = "";
        this.moduleName = Optional.empty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskModule // instanceof handles nulls
                && value.equals(((TaskModule) other).value)); // state check
    }

    public static TaskModule empty() {
        return new TaskModule();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
