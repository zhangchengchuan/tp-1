package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Task in the Task List.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final Name name;
    private final Description description;
    private final Index index;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Description description, Index index) {
        requireAllNonNull(name, description, index);
        this.name = name;
        this.description = description;
        this.index = index;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Index getIndex() {
        return index;
    }
}
