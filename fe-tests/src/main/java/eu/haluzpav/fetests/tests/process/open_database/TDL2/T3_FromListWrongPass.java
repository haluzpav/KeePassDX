package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T3_FromListWrongPass extends BaseDbProcessTest {

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
    public void s03_doNotWantNew() {
        // nothing to test
    }

    @Test
    public void s04_chooseFromList() {
        chooseFromListTest(0);
    }

    @Test
    public void s05_enterWrongPass() {
        enterWrongPassTest(invalidPasswords.get(0));
    }

    @Test
    public void s06_enterCorrectPass() {
        enterCorrectPassTest(validPassword);
    }

}
