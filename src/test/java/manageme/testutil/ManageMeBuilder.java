package manageme.testutil;

import manageme.model.task.Task;
import manageme.model.ManageMe;
import manageme.model.module.Module;
import manageme.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ManageMeBuilder {

    private ManageMe manageMe;

    public ManageMeBuilder() {
        manageMe = new ManageMe();
    }

    public ManageMeBuilder(ManageMe manageMe) {
        this.manageMe = manageMe;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ManageMeBuilder withPerson(Person person) {
        manageMe.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ManageMeBuilder withModule(Module module) {
        manageMe.addModule(module);
        return this;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ManageMeBuilder withTask(Task task) {
        manageMe.addTask(task);
        return this;
    }

    public ManageMe build() {
        return manageMe;
    }
}
