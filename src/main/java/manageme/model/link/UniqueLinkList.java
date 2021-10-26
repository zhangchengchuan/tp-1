package manageme.model.link;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manageme.commons.util.CollectionUtil;
import manageme.model.link.exceptions.DuplicateLinkException;
import manageme.model.link.exceptions.LinkNotFoundException;
import manageme.model.module.Module;

/**
 * A list of links that enforces uniqueness between its elements and does not allow nulls.
 * A link is considered unique by comparing using {@code Link#isSameLink(Link)}. As such, adding and updating of
 * links uses Link#isSameLink(Link) for equality so as to ensure that the link being added or updated is
 * unique in terms of identity in the UniqueLinkList. However, the removal of a link uses Link#equals(Object) so
 * as to ensure that the link with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Link#isSameLink(Link)
 */
public class UniqueLinkList implements Iterable<Link> {

    private final ObservableList<Link> internalList = FXCollections.observableArrayList();
    private final ObservableList<Link> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent link as the given argument.
     */
    public boolean contains(Link toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLink);
    }

    /**
     * Adds a link to the list.
     * The link must not already exist in the list.
     */
    public void add(Link toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLinkException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the link {@code target} in the list with {@code editedLink}.
     * {@code target} must exist in the list.
     * The link identity of {@code editedLink} must not be the same as another existing link in the list.
     */
    public void setLink(Link target, Link editedLink) {
        CollectionUtil.requireAllNonNull(target, editedLink);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LinkNotFoundException();
        }

        if (!target.isSameLink(editedLink) && contains(editedLink)) {
            throw new DuplicateLinkException();
        }

        internalList.set(index, editedLink);
    }

    /**
     * Removes the equivalent link from the list.
     * The link must exist in the list.
     */
    public void remove(Link toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LinkNotFoundException();
        }
    }

    public void removeMod(Module module) {
        requireNonNull(module);
        int i = 0;
        while (i < internalList.size()) {
            if (internalList.get(i).getLinkModule().equals(module)) {
                remove(internalList.get(i));
            } else {
                i++;
            }
        }
    }

    /**
     * Removes the equivalent link from the list.
     * The link must exist in the list.
     */
    public void open(Link toOpen) {
        requireNonNull(toOpen);
        toOpen.open();
    }

    public void setLinks(UniqueLinkList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code links}.
     * {@code links} must not contain duplicate links.
     */
    public void setLinks(List<Link> links) {
        CollectionUtil.requireAllNonNull(links);
        if (!linksAreUnique(links)) {
            throw new DuplicateLinkException();
        }

        internalList.setAll(links);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Link> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Link> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLinkList // instanceof handles nulls
                && internalList.equals(((UniqueLinkList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code links} contains only unique links.
     */
    private boolean linksAreUnique(List<Link> links) {
        for (int i = 0; i < links.size() - 1; i++) {
            for (int j = i + 1; j < links.size(); j++) {
                if (links.get(i).isSameLink(links.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
