package manageme.model.module;

import static manageme.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import manageme.model.module.exceptions.DuplicateModuleException;
import manageme.model.module.exceptions.ModuleNotFoundException;
import manageme.testutil.Assert;
import manageme.testutil.ModuleBuilder;
import manageme.testutil.TypicalModules;

public class UniqueModuleListTest {
    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(TypicalModules.MODULE_A));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        assertTrue(uniqueModuleList.contains(TypicalModules.MODULE_A));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        Module editedAlice = new ModuleBuilder(TypicalModules.MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        assertTrue(uniqueModuleList.contains(editedAlice));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        Assert.assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(TypicalModules.MODULE_A));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(null, TypicalModules.MODULE_A));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(TypicalModules.MODULE_A, null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
        Assert.assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.setModule(TypicalModules.MODULE_A, TypicalModules.MODULE_A));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        uniqueModuleList.setModule(TypicalModules.MODULE_A, TypicalModules.MODULE_A);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.MODULE_A);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        Module editedAlice = new ModuleBuilder(TypicalModules.MODULE_A).withLink(VALID_LINK_GOOGLE).build();
        uniqueModuleList.setModule(TypicalModules.MODULE_A, editedAlice);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(editedAlice);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        uniqueModuleList.setModule(TypicalModules.MODULE_A, TypicalModules.MODULE_B);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.MODULE_B);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        uniqueModuleList.add(TypicalModules.MODULE_B);
        Assert.assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModule(TypicalModules.MODULE_A, TypicalModules.MODULE_B));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueModuleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        Assert.assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(TypicalModules.MODULE_A));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        uniqueModuleList.remove(TypicalModules.MODULE_A);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((UniqueModuleList) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.MODULE_B);
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.add(TypicalModules.MODULE_A);
        List<Module> moduleList = Collections.singletonList(TypicalModules.MODULE_B);
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.MODULE_B);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(TypicalModules.MODULE_A, TypicalModules.MODULE_A);
        Assert.assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () ->
                uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}
