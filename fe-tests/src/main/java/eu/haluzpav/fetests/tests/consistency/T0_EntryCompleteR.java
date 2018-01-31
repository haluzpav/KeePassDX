package eu.haluzpav.fetests.tests.consistency;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import eu.haluzpav.fetests.model.Entry;
import eu.haluzpav.fetests.model.screens.GroupScreen;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.BaseProcessData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T0_EntryCompleteR extends BaseConsistencyTest {

    // TODO test dates

    private static final Entry entry = new Entry() {{
        title = "entricek";
        username = "user neuser";
        url = "https://docs.google.com/";
        pass = "heslo423";
        confPass = pass;
        comments = "this is very legitimate entry";
    }};

    private static final Entry entryEdited = new Entry() {{
        title = "entricek editovany";
        username = "user";
        url = entry.url;
        pass = "bzzzt";
        confPass = pass;
        comments = "this is very editted entry";
    }};

    @Test
    public void s00_openGroup() {
        tryGoToOpenDbScreenTest();
        enterCorrectPathTest(BaseProcessData.defaultDatabasePath);
        enterCorrectPassTest(BaseProcessData.validPassword);

        Assert.assertFalse(groupScreen.hasEntry(entry));
    }

    @Test
    public void s01_createEntry() {
        openAddEntryTest();

        editEntryScreen.fillValues(entry);
        editEntryScreen.save();

        Assert.assertTrue(groupScreen.isOpened());
    }

    @Test
    public void s02_isCreatedInGroup() {
        Assert.assertTrue(groupScreen.hasEntry(entry));
    }

    @Test
    public void s03_hasCreatedAllFields() {
        openReadEntryTest(entry);

        Assert.assertTrue(readEntryScreen.isSameAs(entry));
    }

    @Test
    public void s04_editEntry() {
        openEditEntryTest();

        editEntryScreen.fillValues(entryEdited);
        editEntryScreen.save();

        Assert.assertTrue(readEntryScreen.isOpened());
    }

    @Test
    public void s05_isEditedOnRead() {
        Assert.assertTrue(readEntryScreen.isSameAs(entryEdited));
    }

    @Test
    public void s06_isEditedInGroup() {
        readEntryScreen.toolbar().goBack();
        Assert.assertTrue(groupScreen.isOpened());

        Assert.assertTrue(groupScreen.hasEntry(entryEdited));
    }

    @Test
    public void s07_deleteFromBin() {
        // delete from root group
        groupScreen.deleteEntry(entryEdited);
        Assert.assertFalse(groupScreen.hasEntry(entryEdited));

        // go to bin
        groupScreen.openGroup(BaseProcessData.binGroupName);
        groupScreen = new GroupScreen();
        Assert.assertTrue(groupScreen.isOpened());
        Assert.assertTrue(groupScreen.hasEntry(entryEdited));

        // delete from bin
        groupScreen.deleteEntry(entryEdited);
        Assert.assertFalse(groupScreen.hasEntry(entryEdited));

        // make sure it did not reappeared in root
        groupScreen.toolbar().goBack();
        groupScreen = new GroupScreen(false);
        Assert.assertFalse(groupScreen.hasEntry(entryEdited));
    }
}
