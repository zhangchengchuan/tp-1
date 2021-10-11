package seedu.address.testutil;

import seedu.address.model.module.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalModules {
    public static final Module MODULE_A = new ModuleBuilder().withName("CS111")
            .withLink("https://www.google.com").build();
    public static final Module MODULE_B = new ModuleBuilder().withName("CS222")
            .withLink("https://www.github.com").build();

    private TypicalModules() {} // prevents instantiation

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(MODULE_A, MODULE_B));
    }
}
