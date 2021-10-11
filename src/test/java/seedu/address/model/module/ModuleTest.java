package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODNAME_CS2100;
import static seedu.address.testutil.TypicalModules.MODULE_CS111;
import static seedu.address.testutil.TypicalModules.MODULE_CS2100;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(MODULE_CS111.isSameModule(MODULE_CS111));

        // null -> returns false
        assertFalse(MODULE_CS111.isSameModule(null));

        // same name, all other attributes different -> returns true
        Module editedCS111 = new ModuleBuilder(MODULE_CS111).withLink(VALID_LINK_GOOGLE).build();
        assertTrue(MODULE_CS111.isSameModule(editedCS111));

        // different name, all other attributes same -> returns false
        editedCS111 = new ModuleBuilder(MODULE_CS111).withName(VALID_MODNAME_CS2100).build();
        assertFalse(MODULE_CS111.isSameModule(editedCS111));

        // name differs in case, all other attributes same -> returns false
        Module editedCS2100 = new ModuleBuilder(MODULE_CS2100).withName(VALID_MODNAME_CS2100.toLowerCase()).build();
        assertFalse(MODULE_CS2100.isSameModule(editedCS2100));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_MODNAME_CS2100 + " ";
        editedCS2100 = new ModuleBuilder(MODULE_CS2100).withName(nameWithTrailingSpaces).build();
        assertFalse(MODULE_CS2100.isSameModule(editedCS2100));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module moduleCS111Copy = new ModuleBuilder(MODULE_CS111).build();
        assertTrue(MODULE_CS111.equals(moduleCS111Copy));

        // same object -> returns true
        assertTrue(MODULE_CS111.equals(MODULE_CS111));

        // null -> returns false
        assertFalse(MODULE_CS111.equals(null));

        // different type -> returns false
        assertFalse(MODULE_CS111.equals(5));

        // different Module object -> returns false
        assertFalse(MODULE_CS111.equals(MODULE_CS2100));

        // different name -> returns false
        Module editedCS111 = new ModuleBuilder(MODULE_CS111).withName(VALID_MODNAME_CS2100).build();
        assertFalse(MODULE_CS111.equals(editedCS111));

        // different Link -> returns false
        editedCS111 = new ModuleBuilder(MODULE_CS111).withLink(VALID_LINK_GOOGLE).build();
        assertFalse(MODULE_CS111.equals(editedCS111));
    }
}
