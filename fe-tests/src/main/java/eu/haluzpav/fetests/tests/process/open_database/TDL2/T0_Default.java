package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.BaseData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T0_Default extends BaseDbProcessTest {

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
    public void s02_enterPass() {
        enterCorrectPassTest(BaseData.validPassword);
    }

}
