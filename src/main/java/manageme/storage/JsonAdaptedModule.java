package manageme.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import manageme.commons.exceptions.IllegalValueException;
import manageme.model.link.Link;
import manageme.model.module.Module;
import manageme.model.module.ModuleName;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String modName;
    private final String link;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("modName") String modName, @JsonProperty("link") String link) {
        this.modName = modName;
        this.link = link;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        modName = source.getModuleName().name;
        link = source.getLink().link;
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {

        if (modName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "modName"));
        }
        if (!ModuleName.isValidModuleName(modName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelName = new ModuleName(modName);

        if (link == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "link"));
        }
        if (!Link.isValidLink(link)) {
            throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS);
        }
        final Link modelLink = new Link(link);

        return new Module(modelName, modelLink);
    }
}

