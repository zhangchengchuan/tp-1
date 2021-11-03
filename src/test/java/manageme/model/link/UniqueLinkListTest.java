package manageme.model.link;

import static manageme.testutil.Assert.assertThrows;
import static manageme.testutil.TypicalLinks.LINK_A;
import static manageme.testutil.TypicalLinks.LINK_B;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import manageme.model.link.exceptions.DuplicateLinkException;
import manageme.model.link.exceptions.LinkNotFoundException;
import manageme.testutil.LinkBuilder;

public class UniqueLinkListTest {
    private final UniqueLinkList uniqueLinkList = new UniqueLinkList();

    @Test
    public void contains_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.contains(null));
    }

    @Test
    public void contains_linkNotInList_returnsFalse() {
        assertFalse(uniqueLinkList.contains(LINK_A));
    }

    @Test
    public void contains_linkInList_returnsTrue() {
        uniqueLinkList.add(LINK_A);
        assertTrue(uniqueLinkList.contains(LINK_A));
    }

    @Test
    public void contains_linkWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLinkList.add(LINK_A);
        Link editedAlice = new LinkBuilder(LINK_A).build();
        assertTrue(uniqueLinkList.contains(editedAlice));
    }

    @Test
    public void add_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.add(null));
    }

    @Test
    public void add_duplicateLink_throwsDuplicateLinkException() {
        uniqueLinkList.add(LINK_A);
        assertThrows(DuplicateLinkException.class, () -> uniqueLinkList.add(LINK_A));
    }

    @Test
    public void setLink_nullTargetLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLink(null, LINK_A));
    }

    @Test
    public void setLink_nullEditedLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLink(LINK_A, null));
    }

    @Test
    public void setLink_targetLinkNotInList_throwsLinkNotFoundException() {
        assertThrows(LinkNotFoundException.class, () -> uniqueLinkList.setLink(LINK_A, LINK_A));
    }

    @Test
    public void setLink_editedLinkIsSameLink_success() {
        uniqueLinkList.add(LINK_A);
        uniqueLinkList.setLink(LINK_A, LINK_A);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_A);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLink_editedLinkHasSameIdentity_success() {
        uniqueLinkList.add(LINK_A);
        Link editedLink = new LinkBuilder(LINK_A).withName("edited").build();
        uniqueLinkList.setLink(LINK_A, editedLink);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(editedLink);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLink_editedLinkHasDifferentIdentity_success() {
        uniqueLinkList.add(LINK_A);
        uniqueLinkList.setLink(LINK_A, LINK_B);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_B);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLink_editedLinkHasNonUniqueIdentity_throwsDuplicateLinkException() {
        uniqueLinkList.add(LINK_A);
        uniqueLinkList.add(LINK_B);
        assertThrows(DuplicateLinkException.class, () -> uniqueLinkList.setLink(LINK_A, LINK_B));
    }

    @Test
    public void remove_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.remove(null));
    }

    @Test
    public void remove_linkDoesNotExist_throwsLinkNotFoundException() {
        assertThrows(LinkNotFoundException.class, () -> uniqueLinkList.remove(LINK_A));
    }

    @Test
    public void remove_existingLink_removesLink() {
        uniqueLinkList.add(LINK_A);
        uniqueLinkList.remove(LINK_A);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLinks_nullUniqueLinkList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLinks((UniqueLinkList) null));
    }

    @Test
    public void setLinks_uniqueLinkList_replacesOwnListWithProvidedUniqueLinkList() {
        uniqueLinkList.add(LINK_A);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_B);
        uniqueLinkList.setLinks(expectedUniqueLinkList);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLinks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLinkList.setLinks((List<Link>) null));
    }

    @Test
    public void setLinks_list_replacesOwnListWithProvidedList() {
        uniqueLinkList.add(LINK_A);
        List<Link> linkList = Collections.singletonList(LINK_B);
        uniqueLinkList.setLinks(linkList);
        UniqueLinkList expectedUniqueLinkList = new UniqueLinkList();
        expectedUniqueLinkList.add(LINK_B);
        assertEquals(expectedUniqueLinkList, uniqueLinkList);
    }

    @Test
    public void setLinks_listWithDuplicateLinks_throwsDuplicateLinkException() {
        List<Link> listWithDuplicateLinks = Arrays.asList(LINK_A, LINK_A);
        assertThrows(DuplicateLinkException.class, () -> uniqueLinkList.setLinks(listWithDuplicateLinks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueLinkList.asUnmodifiableObservableList().remove(0));
    }
}
