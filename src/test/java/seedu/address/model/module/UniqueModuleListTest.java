package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_A;
import static seedu.address.testutil.TypicalModules.MODULE_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.testutil.ModuleBuilder;

public class UniqueModuleListTest {
    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(MODULE_A));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.add(MODULE_A);
        assertTrue(uniqueModuleList.contains(MODULE_A));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleList.add(MODULE_A);
        Module editedAlice = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        assertTrue(uniqueModuleList.contains(editedAlice));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        uniqueModuleList.add(MODULE_A);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(MODULE_A));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(null, MODULE_A));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(MODULE_A, null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.setModule(MODULE_A, MODULE_A));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        uniqueModuleList.add(MODULE_A);
        uniqueModuleList.setModule(MODULE_A, MODULE_A);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(MODULE_A);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
        uniqueModuleList.add(MODULE_A);
        Module editedAlice = new ModuleBuilder(MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        uniqueModuleList.setModule(MODULE_A, editedAlice);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(editedAlice);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        uniqueModuleList.add(MODULE_A);
        uniqueModuleList.setModule(MODULE_A, MODULE_B);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(MODULE_B);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
        uniqueModuleList.add(MODULE_A);
        uniqueModuleList.add(MODULE_B);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModule(MODULE_A, MODULE_B));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(MODULE_A));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.add(MODULE_A);
        uniqueModuleList.remove(MODULE_A);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((UniqueModuleList) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.add(MODULE_A);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(MODULE_B);
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.add(MODULE_A);
        List<Module> moduleList = Collections.singletonList(MODULE_B);
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(MODULE_B);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(MODULE_A, MODULE_A);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}
