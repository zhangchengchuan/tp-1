package seedu.address.testutil;

import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical persons, modules and tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }

        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }
}
