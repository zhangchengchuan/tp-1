package manageme.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageme.testutil.Assert;

public class ModuleNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_invalidModuleName_throwsIllegalArgumentException() {
        String invalidModuleName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidModuleName));
    }

    @Test
    public void isValidModuleName() {
        // null ModuleName
        Assert.assertThrows(NullPointerException.class, () -> ModuleName.isValidModuleName(null));

        // invalid ModuleName
        assertFalse(ModuleName.isValidModuleName("")); // empty string
        assertFalse(ModuleName.isValidModuleName(" ")); // spaces only
        assertFalse(ModuleName.isValidModuleName("^")); // only non-alphanumeric characters
        assertFalse(ModuleName.isValidModuleName("CS2100*")); // contains non-alphanumeric characters

        // valid ModuleName
        assertTrue(ModuleName.isValidModuleName("CSCS")); // alphabets only
        assertTrue(ModuleName.isValidModuleName("12345")); // numbers only
        assertTrue(ModuleName.isValidModuleName("cs2100")); // alphanumeric characters
        assertTrue(ModuleName.isValidModuleName("CS2100")); // with capital letters
        assertTrue(ModuleName.isValidModuleName("CS2100 Computer Organization")); // long ModuleNames
    }
}
