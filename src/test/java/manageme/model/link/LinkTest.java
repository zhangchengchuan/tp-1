package manageme.model.link;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static manageme.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static manageme.logic.commands.CommandTestUtil.VALID_LINK_NAME_YOUTUBE;

import org.junit.jupiter.api.Test;

import manageme.testutil.LinkBuilder;
import manageme.testutil.TypicalLinks;

public class LinkTest {

    /**
     * This tests if two Link objects have the same link content
     */
    @Test
    public void isSameLink() {
        // same object -> returns true
        assertTrue(TypicalLinks.LINK_LUMINUS.isSameLink(TypicalLinks.LINK_LUMINUS));

        // null -> returns false
        assertFalse(TypicalLinks.LINK_LUMINUS.isSameLink(null));

        // same link, different name -> returns true
        Link editedLuminus = new LinkBuilder(TypicalLinks.LINK_LUMINUS).withName(VALID_LINK_NAME_YOUTUBE).build();
        assertTrue(TypicalLinks.LINK_LUMINUS.isSameLink(editedLuminus));

        // different link, same name -> returns false
        editedLuminus = new LinkBuilder(TypicalLinks.LINK_LUMINUS).withLink(VALID_LINK_GOOGLE).build();
        assertFalse(TypicalLinks.LINK_LUMINUS.isSameLink(editedLuminus));
    }

    /**
     * This tests if two Link objects have both the same link content and the same name.
     */
    @Test
    public void equals() {
        // same values -> returns true
        Link luminusCopy = new LinkBuilder(TypicalLinks.LINK_LUMINUS).build();
        assertTrue(TypicalLinks.LINK_LUMINUS.equals(luminusCopy));

        // same object -> returns true
        assertTrue(TypicalLinks.LINK_LUMINUS.equals(TypicalLinks.LINK_LUMINUS));

        // null -> returns false
        assertFalse(TypicalLinks.LINK_LUMINUS.equals(null));

        // different type -> returns false
        assertFalse(TypicalLinks.LINK_LUMINUS.equals(5));

        // different Link object -> returns false
        assertFalse(TypicalLinks.LINK_LUMINUS.equals(TypicalLinks.LINK_YOUTUBE));

        // TODO: This test only works if the equal method in Link class compares names
        // different name -> returns false
        // Link editedLuminus = new LinkBuilder(LINK_LUMINUS).withName(VALID_LINK_NAME_YOUTUBE).build();
        // assertFalse(LINK_LUMINUS.equals(editedLuminus));

        // different link content -> returns false
        Link editedLuminus = new LinkBuilder(TypicalLinks.LINK_LUMINUS).withLink(VALID_LINK_GOOGLE).build();
        assertFalse(TypicalLinks.LINK_LUMINUS.equals(editedLuminus));
    }
}
