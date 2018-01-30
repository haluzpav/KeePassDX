package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Collection;

import eu.haluzpav.fetests.model.dialogs.CreateDbPassDialog;
import eu.haluzpav.fetests.model.dialogs.CreateDbPathDialog;
import eu.haluzpav.fetests.model.screens.OpenDbScreen;
import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.T4_Data;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T4_NewFromPath extends BaseDbProcessTest {

    @Parameterized.Parameter(0)
    public String dbPathRoot;

    @Parameterized.Parameter(1)
    public String dbPathFilename;

    @Parameterized.Parameter(2)
    public String validPassword;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return new T4_Data().data();
    }

    @Test
    public void s00_isAppOpened() {
        isAppOpenedTest();
    }

    @Test
    public void s01_isNotOnDefault() {
        tryGoToOpenDbScreenTest();
    }

    @Test
    public void s02_databaseExists() {
        databaseExistsTest();
    }

    @Test
    public void s03_wantNew() {
        // nothing to test
    }

    @Test
    public void s04_openCreate() {
        openDbScreen.openCreateDatabase();

        createDbPathDialog = new CreateDbPathDialog();

        Assert.assertTrue(createDbPathDialog.isOpened());
    }

    @Test
    public void s05_createPath() {
        createDbPathDialog.enterPath(dbPathRoot);
        createDbPathDialog.enterFilename(dbPathFilename);
        createDbPathDialog.confirm();

        createDbPassDialog = new CreateDbPassDialog();

        Assert.assertFalse(createDbPathDialog.isOpened());
        Assert.assertTrue(createDbPassDialog.isOpened());
    }

    @Test
    public void s06_createPass() {
        createDbPassDialog.enterPassword(validPassword, validPassword);
        createDbPassDialog.confirm();

        openDbScreen = new OpenDbScreen();
        Assert.assertTrue(openDbScreen.isOpened());
    }

    @Test
    public void s07_choosePath() {
        // nothing to test
    }

    @Test
    public void s08_enterPath() {
        enterCorrectPathTest(dbPathRoot + dbPathFilename);
    }

    @Test
    public void s09_enterPass() {
        enterCorrectPassTest(validPassword);
    }

}
