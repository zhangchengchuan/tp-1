package manageme.model.link;

import static manageme.testutil.Assert.assertThrows;
import static manageme.testutil.TypicalLinks.LINK_A;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkModule(null));
    }
    @Test
    public void constructor_invalidLinkModule_throwsIllegalArgumentException() {
        String invalidModule = "**";
        assertThrows(IllegalArgumentException.class, () -> new LinkModule(invalidModule));
    }
    @Test
    public void isValidModule() {
        // null module
        assertThrows(NullPointerException.class, () -> LinkModule.isValidModule(null));

        // invalid module
        assertFalse(LinkModule.isValidModule("^")); // only non-alphanumeric characters
        assertFalse(LinkModule.isValidModule("hello*")); // contains non-alphanumeric characters

        // valid module
        assertTrue(LinkModule.isValidModule("CSABCD")); // alphabets only
        assertTrue(LinkModule.isValidModule("12345")); // numbers only
        assertTrue(LinkModule.isValidModule("cs2100")); // alphanumeric characters
        assertTrue(LinkModule.isValidModule("CS2103T")); // with capital letters
    }
    @Test
    public void equals() {
        // same module, returns true
        assertTrue(LINK_A.getLinkModule().equals(LINK_A.getLinkModule()));
    }
    @Test
    public void empty() {
        // Empty LinkModule with value set as ""
        assertTrue(LinkModule.empty().value.equals(""));

        // Empty LinkModule with moduleName set as an empty Optional<String>
        assertTrue(LinkModule.empty().moduleName.isEmpty());
    }
}
