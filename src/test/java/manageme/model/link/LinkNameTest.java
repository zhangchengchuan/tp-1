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
        assertThrows(NullPointerException.class, () -> LinkName.isValidName(null));

        // invalid name
        assertFalse(LinkName.isValidName("")); // empty string
        assertFalse(LinkName.isValidName(" ")); // spaces only

        // valid name
        assertTrue(LinkName.isValidName("google")); // alphabets only
        assertTrue(LinkName.isValidName("12345")); // numbers only
        assertTrue(LinkName.isValidName("notes for cs1101")); // alphanumeric characters
        assertTrue(LinkName.isValidName("assignments for CS1101")); // with capital letters
        assertTrue(LinkName.isValidName("Plan out next week timetable by this Friday")); // long names
    }

    @Test
    public void equals() {
        // same name, returns true
        assertTrue(LINK_A.getName().equals(LINK_A.getName()));

    }
}
