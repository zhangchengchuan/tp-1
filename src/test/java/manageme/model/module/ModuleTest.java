package manageme.model.module;

import static manageme.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static manageme.logic.commands.CommandTestUtil.VALID_MODNAME_CS2100;
import static manageme.testutil.TypicalModules.MODULE_A;
import static manageme.testutil.TypicalModules.MODULE_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageme.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(MODULE_A.isSameModule(MODULE_A));

        // null -> returns false
        assertFalse(MODULE_A.isSameModule(null));

        // same name, all other attributes different -> returns true
        Module editedCS111 = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        assertTrue(MODULE_A.isSameModule(editedCS111));

        // different name, all other attributes same -> returns false
        editedCS111 = new ModuleBuilder(MODULE_A).withName(VALID_MODNAME_CS2100).build();
        assertFalse(MODULE_A.isSameModule(editedCS111));

        // name differs in case, all other attributes same -> returns false
        Module editedCS2100 = new ModuleBuilder(MODULE_B).withName(VALID_MODNAME_CS2100.toLowerCase()).build();
        assertFalse(MODULE_B.isSameModule(editedCS2100));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_MODNAME_CS2100 + " ";
        editedCS2100 = new ModuleBuilder(MODULE_B).withName(nameWithTrailingSpaces).build();
        assertFalse(MODULE_B.isSameModule(editedCS2100));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module moduleCS111Copy = new ModuleBuilder(MODULE_A).build();
        assertTrue(MODULE_A.equals(moduleCS111Copy));

        // same object -> returns true
        assertTrue(MODULE_A.equals(MODULE_A));

        // null -> returns false
        assertFalse(MODULE_A.equals(null));

        // different type -> returns false
        assertFalse(MODULE_A.equals(5));

        // different Module object -> returns false
        assertFalse(MODULE_A.equals(MODULE_B));

        // different name -> returns false
        Module editedCS111 = new ModuleBuilder(MODULE_A).withName(VALID_MODNAME_CS2100).build();
        assertFalse(MODULE_A.equals(editedCS111));

        // different Link -> returns false
        editedCS111 = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        assertFalse(MODULE_A.equals(editedCS111));
    }
}
