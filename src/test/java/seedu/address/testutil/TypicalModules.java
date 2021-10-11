package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;

public class TypicalModules {
    public static final Module MODULE_A = new ModuleBuilder().withName("CS111")
            .withLink("https://www.facebook.com").build();
    public static final Module MODULE_B = new ModuleBuilder().withName("CS2100")
            .withLink("https://www.github.com").build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(MODULE_A, MODULE_B));
    }
}
