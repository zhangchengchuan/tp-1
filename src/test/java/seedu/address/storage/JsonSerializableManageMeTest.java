package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ManageMe;
import seedu.address.testutil.TypicalManageMe;
import seedu.address.testutil.TypicalTasks;

public class JsonSerializableManageMeTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableManageMeTest");
    private static final Path TYPICAL_MANAGE_ME_FILE = TEST_DATA_FOLDER.resolve("typicalManageMe.json");
    private static final Path TEST_TASK_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableManageMeTest", "Task");
    private static final Path TYPICAL_TASKS_FILE = TEST_TASK_DATA_FOLDER.resolve("typicalTasksManageMe.json");
    private static final Path INVALID_TASK_FILE = TEST_TASK_DATA_FOLDER.resolve("invalidTaskManageMe.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_TASK_DATA_FOLDER.resolve("duplicateTaskManageMe.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path DUPLICATE_MODULE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleManageMe.json");
    //private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskManageMe.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableManageMe dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableManageMe.class).get();
        ManageMe manageMeFromFile = dataFromFile.toModelType();
        ManageMe typicalTasksManageMe = TypicalTasks.getTypicalAddressBook();
        assertEquals(manageMeFromFile, typicalTasksManageMe);
    }

    @Test
    public void toModelType_invalidTasksFile_throwsIllegalValueException() throws Exception {
        JsonSerializableManageMe dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableManageMe.class).orElse(null);
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalManageMeFile_success() throws Exception {
        JsonSerializableManageMe dataFromFile = JsonUtil.readJsonFile(TYPICAL_MANAGE_ME_FILE,
                JsonSerializableManageMe.class).get();
        ManageMe manageMeFromFile = dataFromFile.toModelType();
        ManageMe typicalManageMe = TypicalManageMe.getTypicalManageMe();
        assertEquals(manageMeFromFile, typicalManageMe);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableManageMe dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableManageMe.class).orElse(null);
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableManageMe dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableManageMe.class).orElse(null);
        assertThrows(IllegalValueException.class, JsonSerializableManageMe.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        JsonSerializableManageMe dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_FILE,
                JsonSerializableManageMe.class).orElse(null);
        assertThrows(IllegalValueException.class, JsonSerializableManageMe.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableManageMe dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableManageMe.class).orElse(null);
        assertThrows(IllegalValueException.class, JsonSerializableManageMe.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }
}
