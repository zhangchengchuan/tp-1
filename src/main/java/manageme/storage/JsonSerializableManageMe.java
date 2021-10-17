package manageme.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import manageme.commons.exceptions.IllegalValueException;
import manageme.model.ManageMe;
import manageme.model.ReadOnlyManageMe;
import manageme.model.module.Module;
import manageme.model.person.Person;
import manageme.model.task.Task;

/**
 * An Immutable ManageMe that is serializable to JSON format.
 */
@JsonRootName(value = "manageme")
class JsonSerializableManageMe {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate module(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableManageMe} with the given persons.
     */
    @JsonCreator
    public JsonSerializableManageMe(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                    @JsonProperty("modules") List<JsonAdaptedModule> modules,
                                    @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.persons.addAll(persons);
        this.modules.addAll(modules);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyManageMe} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableManageMe}.
     */
    public JsonSerializableManageMe(ReadOnlyManageMe source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this ManageMe into the model's {@code ManageMe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ManageMe toModelType() throws IllegalValueException {
        ManageMe manageMe = new ManageMe();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (manageMe.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            manageMe.addPerson(person);
        }

        for (JsonAdaptedModule jsonAdaptedModule: modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (manageMe.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            manageMe.addModule(module);
        }

        for (JsonAdaptedTask jsonAdaptedTask: tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (manageMe.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            manageMe.addTask(task);
        }
        return manageMe;
    }

}
