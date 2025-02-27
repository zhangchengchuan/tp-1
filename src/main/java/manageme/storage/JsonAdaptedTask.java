package manageme.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import manageme.commons.exceptions.IllegalValueException;
import manageme.model.task.Task;
import manageme.model.task.TaskDescription;
import manageme.model.task.TaskIsDone;
import manageme.model.task.TaskModule;
import manageme.model.task.TaskName;
import manageme.model.task.TaskTime;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    // 3 Optional - time/end-time/modules
    private final String taskName;
    private final String taskDescription;
    private final String isDone;
    private final String module;
    private final String start;
    private final String end;
    // private final List<JsonAdaptedTag> tagged = new ArrayList<>(); // Link to Module


    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String taskName, @JsonProperty("description") String description,
                           @JsonProperty("isDone") String isDone, @JsonProperty("module") String module,
                           @JsonProperty("start") String start,
                           @JsonProperty("end") String end) {
        //                   @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.taskName = taskName;
        this.taskDescription = description;
        this.isDone = isDone;
        this.module = module;
        this.start = start;
        this.end = end;
        //if (tagged != null) {
        //    this.tagged.addAll(tagged);
        //  }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        this.taskName = source.getName().value;
        this.taskDescription = source.getDescription().value;
        this.isDone = source.isDone().toString();
        //tagged.addAll(source.getTag().stream()
        //        .map(JsonAdaptedTag::new)
        //        .collect(Collectors.toList());
        this.module = source.getTaskModule().value;
        this.start = source.getStart().value;
        this.end = source.getEnd().value;
    }


    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        //final List<Tag> taskTags = new ArrayList<>();
        //for (JsonAdaptedTag tag : tagged) {
        //    taskTags.add(tag.toModelType());
        //}

        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "TaskName"));
        }
        if (!TaskName.isValidName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(taskName);

        if (taskDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "TaskDescription"));
        }
        if (!TaskDescription.isValidDescription(taskDescription)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelDescription = new TaskDescription(taskDescription);

        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "TaskIsDone"));
        }

        if (!isDone.equals("yes") && !isDone.equals("no")) {
            throw new IllegalValueException(TaskIsDone.MESSAGE_CONSTRAINTS);
        }

        final TaskIsDone modelIsDone = new TaskIsDone(isDone.equals("yes"));

        final TaskModule modelModule = !module.equals("") ? new TaskModule(module) : TaskModule.empty();

        final TaskTime modelStart = !start.equals("") ? new TaskTime(start) : TaskTime.empty();

        final TaskTime modelEnd = !end.equals("") ? new TaskTime(end) : TaskTime.empty();

        // final Set<Tag> modelTags = new HashSet<>(taskTags);
        Task createdTask = new Task(modelName, modelDescription, modelIsDone, modelModule, modelStart,
                modelEnd);

        return createdTask;
    }
}
