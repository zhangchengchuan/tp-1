package seedu.address.model.task;

public class Todo extends Task {
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
}
