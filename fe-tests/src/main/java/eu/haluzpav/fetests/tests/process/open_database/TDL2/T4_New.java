package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

import eu.haluzpav.fetests.model.dialogs.CreateDbPassScreen;
import eu.haluzpav.fetests.model.dialogs.CreateDbPathScreen;
import eu.haluzpav.fetests.model.screens.OpenDbScreen;
import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T4_New extends BaseDbProcessTest {

    private static String randomFilename = String.valueOf(new Random().nextInt()) + ".kdbx";

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
        Assert.assertTrue(databaseExists());
    }

    @Test
    public void s03_wantNew() {
        // nothing to test
    }

    @Test
    public void s04_openCreate() {
        openDbScreen.openCreateDatabase();

        createDbPathScreen = new CreateDbPathScreen();

        Assert.assertTrue(createDbPathScreen.isOpened());
    }

    @Test
    public void s05_createPath() {
        createDbPathScreen.enterPath(databaseRoot);
        createDbPathScreen.enterFilename(randomFilename);
        createDbPathScreen.confirm();

        createDbPassScreen = new CreateDbPassScreen();

        Assert.assertFalse(createDbPathScreen.isOpened());
        Assert.assertTrue(createDbPassScreen.isOpened());
    }

    @Test
    public void s06_createPass() {
        createDbPassScreen.enterPassword(validPassword, validPassword);
        createDbPassScreen.confirm();

        openDbScreen = new OpenDbScreen();
        Assert.assertTrue(openDbScreen.isOpened());
    }

//    @Test
//    public void s08_chooseFromList() {
//        chooseFromListTest(0);
//    }

    @Test
    public void s07_choosePath() {
        // nothing to test
    }

    @Test
    public void s08_enterPath() {
        enterCorrectPathTest(databaseRoot + randomFilename);
    }

    @Test
    public void s09_enterPass() {
        enterCorrectPassTest(validPassword);
    }

}
