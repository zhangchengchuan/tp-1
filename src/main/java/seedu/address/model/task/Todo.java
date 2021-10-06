package seedu.address.model.task;

import java.util.Objects;

import seedu.address.model.module.Module;

public class Todo extends Task {
    /**
     * Constructor for a Todo with module.
     */
    public Todo (TaskName name, TaskDescription description, Module module) {
        super(name, description, module);
    }

    /**
     * Constructor for a Todo without module.
     */
    public Todo (TaskName name, TaskDescription description) {
        super(name, description);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) other;
        return otherTodo.getName().equals(getName())
                && otherTodo.getDescription().equals(getDescription())
                && otherTodo.getModule().equals(getModule());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getDescription(), getModule());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
