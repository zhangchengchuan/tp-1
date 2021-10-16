package manageme.testutil;

import static manageme.testutil.TypicalPersons.getTypicalPersons;

import manageme.model.ManageMe;
import manageme.model.module.Module;
import manageme.model.person.Person;
import manageme.model.task.Task;

public class TypicalManageMe {
    /**
     * Returns an {@code ManageMe} with all the typical persons, modules and tasks.
     */
    public static ManageMe getTypicalManageMe() {
        ManageMe ab = new ManageMe();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        for (Module module : TypicalModules.getTypicalModules()) {
            ab.addModule(module);
        }

        for (Task task : TypicalTasks.getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }
}
