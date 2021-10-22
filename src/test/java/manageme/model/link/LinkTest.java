package manageme.model.link;

import static manageme.testutil.TypicalLinks.LINK_LUMINUS;
import static manageme.testutil.TypicalLinks.LINK_YOUTUBE;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import manageme.testutil.LinkBuilder;

public class LinkTest {

    /**
     * This tests if two Link objects have the same link content
     */
    @Test
    public void isSameLink() {
        // same object -> returns true
        assertTrue(LINK_LUMINUS.isSameLink(LINK_LUMINUS));

        // null -> returns false
        assertFalse(LINK_LUMINUS.isSameLink(null));

        // same link, different name -> returns true
        Link editedLuminus = new LinkBuilder(LINK_LUMINUS).withName("Lecture").build();
        assertTrue(LINK_LUMINUS.isSameLink(editedLuminus));

        // different link, same name -> returns false
        editedLuminus = new LinkBuilder(LINK_LUMINUS).withAddress("www.youtube.com").build();
        assertFalse(LINK_LUMINUS.isSameLink(editedLuminus));
    }

    /**
     * This tests if two Link objects have both the same link content and the same name.
     */
    @Test
    public void equals() {
        // same values -> returns true
        Link luminusCopy = new LinkBuilder(LINK_LUMINUS).build();
        assertTrue(LINK_LUMINUS.equals(luminusCopy));

        // same object -> returns true
        assertTrue(LINK_LUMINUS.equals(LINK_LUMINUS));

        // null -> returns false
        assertFalse(LINK_LUMINUS.equals(null));

        // different type -> returns false
        assertFalse(LINK_LUMINUS.equals(5));

        // different Link object -> returns false
        assertFalse(LINK_LUMINUS.equals(LINK_YOUTUBE));

        // different name -> returns false
         Link differentName = new LinkBuilder(LINK_LUMINUS).withName("differentName").build();
         assertFalse(LINK_LUMINUS.equals(differentName));

        // different link address -> returns false
        Link differentAddress = new LinkBuilder(LINK_LUMINUS).withAddress("www.differentaddress.com").build();
        assertFalse(LINK_LUMINUS.equals(differentAddress));

        // different link module -> return false
        Link differentModule = new LinkBuilder(LINK_LUMINUS).withModule("differentModule").build();
        assertFalse(LINK_LUMINUS.equals(differentModule));
    }
}
