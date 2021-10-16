package manageme.model.module;

import static manageme.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static manageme.logic.commands.CommandTestUtil.VALID_MODNAME_CS2100;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import manageme.testutil.ModuleBuilder;
import manageme.testutil.TypicalModules;

public class ModuleTest {

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(TypicalModules.MODULE_A.isSameModule(TypicalModules.MODULE_A));

        // null -> returns false
        assertFalse(TypicalModules.MODULE_A.isSameModule(null));

        // same name, all other attributes different -> returns true
        Module editedCS111 = new ModuleBuilder(TypicalModules.MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        assertTrue(TypicalModules.MODULE_A.isSameModule(editedCS111));

        // different name, all other attributes same -> returns false
        editedCS111 = new ModuleBuilder(TypicalModules.MODULE_A).withName(VALID_MODNAME_CS2100).build();
        assertFalse(TypicalModules.MODULE_A.isSameModule(editedCS111));

        // name differs in case, all other attributes same -> returns false
        Module editedCS2100 =
                new ModuleBuilder(TypicalModules.MODULE_B).withName(VALID_MODNAME_CS2100.toLowerCase()).build();
        assertFalse(TypicalModules.MODULE_B.isSameModule(editedCS2100));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_MODNAME_CS2100 + " ";
        editedCS2100 = new ModuleBuilder(TypicalModules.MODULE_B).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalModules.MODULE_B.isSameModule(editedCS2100));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module moduleCS111Copy = new ModuleBuilder(TypicalModules.MODULE_A).build();
        Assertions.assertTrue(TypicalModules.MODULE_A.equals(moduleCS111Copy));

        // same object -> returns true
        Assertions.assertTrue(TypicalModules.MODULE_A.equals(TypicalModules.MODULE_A));

        // null -> returns false
        Assertions.assertFalse(TypicalModules.MODULE_A.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalModules.MODULE_A.equals(5));

        // different Module object -> returns false
        Assertions.assertFalse(TypicalModules.MODULE_A.equals(TypicalModules.MODULE_B));

        // different name -> returns false
        Module editedCS111 = new ModuleBuilder(TypicalModules.MODULE_A).withName(VALID_MODNAME_CS2100).build();
        Assertions.assertFalse(TypicalModules.MODULE_A.equals(editedCS111));

        // different Link -> returns false
        editedCS111 = new ModuleBuilder(TypicalModules.MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        Assertions.assertFalse(TypicalModules.MODULE_A.equals(editedCS111));
    }
}
