package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T2_WrongPathWrongPass extends BaseDbProcessTest {

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
    public void s03_doNotWantNew() {
        // nothing to test
    }

    @Test
    public void s04_choosePath() {
        // nothing to test
    }

    @Test
    public void s05_enterWrongPath() {
        enterWrongPathTest(defaultDatabasePath + "bananas");
    }

    @Test
    public void s06_enterWrongPath() {
        enterWrongPathTest(defaultDatabasePath + "zeman zas vyhral");
    }

    @Test
    public void s07_enterCorrectPath() {
        enterCorrectPathTest(defaultDatabasePath);
    }

    @Test
    public void s08_enterWrongPass() {
        enterWrongPassTest(invalidPasswords.get(0));
    }

    @Test
    public void s09_enterCorrectPass() {
        enterCorrectPassTest(validPassword);
    }

}
