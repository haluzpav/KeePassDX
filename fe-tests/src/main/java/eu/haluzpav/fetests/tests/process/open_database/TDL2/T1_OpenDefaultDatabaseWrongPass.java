package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_OpenDefaultDatabaseWrongPass extends BaseDbProcessTest {

    @Test
    public void s00_isAppOpened() {
        isAppOpenedTest();
    }

    @Test
    public void s01_isOnDefault() {
        Assert.assertTrue(isOnDefault());

        // TODO create DB
    }

    @Test
    public void s02_enterWrongPass() {
        enterWrongPassTest(invalidPasswords.get(0));
    }

    @Test
    public void s03_enterWrongPass() {
        enterWrongPassTest(invalidPasswords.get(1));
    }

    @Test
    public void s04_enterCorrectPass() {
        enterCorrectPassTest(validPassword);
    }

}
