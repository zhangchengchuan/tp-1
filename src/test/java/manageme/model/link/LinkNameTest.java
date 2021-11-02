package manageme.model.link;

import static manageme.testutil.Assert.assertThrows;
import static manageme.testutil.TypicalLinks.LINK_A;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkName(null));
    }

    @Test
    public void constructor_invalidLinkName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new LinkName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> LinkName.isValidLinkName(null));

        // invalid name
        assertFalse(LinkName.isValidLinkName("")); // empty string
        assertFalse(LinkName.isValidLinkName(" ")); // spaces only

        // valid name
        assertTrue(LinkName.isValidLinkName("google")); // alphabets only
        assertTrue(LinkName.isValidLinkName("12345")); // numbers only
        assertTrue(LinkName.isValidLinkName("notes for cs1101")); // alphanumeric characters
        assertTrue(LinkName.isValidLinkName("assignments for CS1101")); // with capital letters
        assertTrue(LinkName.isValidLinkName("Plan out next week timetable by this Friday")); // long names
    }

    @Test
    public void equals() {
        // same name, returns true
        assertTrue(LINK_A.getName().equals(LINK_A.getName()));

    }
}
