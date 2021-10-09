package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskModule;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;

public class TaskParserUtil {
    /**
     * Parses a {@code String task name} into a {@code TaskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code TaskName} is invalid.
     */
    public static TaskName parseTaskName(String taskName) throws ParseException {
        requireNonNull(taskName);
        String trimmedName = taskName.trim();
        if (!TaskName.isValidName(trimmedName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return new TaskName(trimmedName);
    }

    /**
     * Parses a {@code String task description} into a {@code TaskDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code TaskDescription} is invalid.
     */
    public static TaskDescription parseTaskDescription(String taskDescription) {
        requireNonNull(taskDescription);
        String trimmedD = taskDescription.trim();
        return new TaskDescription(trimmedD);
    }

    /**
     * Parses a {@code String task module} into a {@code TaskModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code TaskModule} is invalid.
     */
    public static TaskModule parseTaskModule(String taskModule) {
        requireNonNull(taskModule);
        String trimmedD = taskModule.trim();
        return new TaskModule(trimmedD);
    }

    /**
     * Parses a {@code String date/time} into a {@code TaskTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code TaskTime} is invalid.
     */
    public static TaskTime parseDateTime(String dateTime) {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        return trimmedDateTime.equals("") ? new TaskTime() : new TaskTime(trimmedDateTime);
    }
}
