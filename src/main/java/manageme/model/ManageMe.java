package manageme.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import manageme.model.link.Link;
import manageme.model.link.UniqueLinkList;
import manageme.model.module.Module;
import manageme.model.module.UniqueModuleList;
import manageme.model.task.Task;
import manageme.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed
 */
public class ManageMe implements ReadOnlyManageMe {

    private final UniqueLinkList links;
    private final UniqueModuleList modules;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        links = new UniqueLinkList();
        modules = new UniqueModuleList();
        tasks = new UniqueTaskList();
    }

    public ManageMe() {}

    /**
     * Creates an ManageMe using the links, tasks, and modules in the {@code toBeCopied}
     */
    public ManageMe(ReadOnlyManageMe toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations


    public <T extends ManageMeObject> void setTs(List<T> lists) {
        UniqueObjectList<T> toSet = getList(lists.get(0));
        toSet.setTs(lists);
    }

    public <T extends ManageMeObject> void setT(T target, T editedT) {
        UniqueObjectList<T> toSet = getList(target);
        toSet.setT(target, editedT);
    }

    /**
     *
     * @param toAdd
     * @param <T>
     */
    public <T extends ManageMeObject> void add(T toAdd) {
        UniqueObjectList<T> toSet = getList(toAdd);
        toSet.add(toAdd);
    }

    /**
     *
     * @param target
     * @param <T>
     * @return
     */
    public <T extends ManageMeObject> boolean has(T target) {
        UniqueObjectList<T> toSet = getList(target);
        return toSet.contains(target);
    }

    /**
     *
     * @param target
     * @param <T>
     */
    public <T extends ManageMeObject> void remove(T target) {
        UniqueObjectList<T> toSet = getList(target);
        toSet.remove(target);
        if (target instanceof Module) {
            Module modTarget = (Module) target;
            links.removeMod(modTarget);
        }
    }

    /**
     *
     * @param object
     * @param <T>
     * @return
     */
    public <T extends ManageMeObject> UniqueObjectList<T> getList(T object) {
        if (object instanceof Task) {
            return (UniqueObjectList<T>) tasks;
        } else if (object instanceof Link) {
            return (UniqueObjectList<T>) links;
        } else {
            return (UniqueObjectList<T>) modules;
        }
    }

    /**
     * Resets the existing data of this {@code ManageMe} with {@code newData}.
     */
    public void resetData(ReadOnlyManageMe newData) {
        requireNonNull(newData);
        this.setTs(newData.getLinkList());
        this.setTs(newData.getModuleList());
        this.setTs(newData.getTaskList());
    }

    //// link-level operations

    public void openLink(Link key) {
        links.open(key);
    }

    //// util methods

    @Override
    public String toString() {
        return links.asUnmodifiableObservableList().size() + " links\n"
                + modules.asUnmodifiableObservableList().size() + " modules\n"
                + tasks.asUnmodifiableObservableList().size() + " tasks";
    }

    @Override
    public ObservableList<Link> getLinkList() {
        return links.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ArrayList<Task> getModifiableTaskList() {
        return tasks.asModifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ManageMe // instanceof handles nulls
                && links.equals(((ManageMe) other).links)
                && modules.equals(((ManageMe) other).modules)
                && tasks.equals(((ManageMe) other).tasks));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(links, modules, tasks);
    }
}
