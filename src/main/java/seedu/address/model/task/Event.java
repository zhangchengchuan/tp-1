package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;


public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructor for an Event object without start and end DateTime.
     */
    public Event (TaskName name, TaskDescription description) {
        super(name, description);
        startDateTime = null;
        endDateTime = null;
    }

    /**
     * Constructor for an Event object with start and end DateTime.
     */
    public Event (TaskName name, TaskDescription description, LocalDateTime startDateTime,
                  LocalDateTime endDateTime) {
        super(name, description);
        requireAllNonNull(startDateTime, endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
