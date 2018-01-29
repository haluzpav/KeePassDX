package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Collection;

import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.T3_Data;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T3_FromListWrongPass extends BaseDbProcessTest {

    @Parameterized.Parameter(0)
    public String invalidPassword;

    @Parameterized.Parameter(2)
    public String validPassword;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return new T3_Data().data();
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
    public void s03_doNotWantNew() {
        // nothing to test
    }

    @Test
    public void s04_chooseFromList() {
        chooseFromListTest(0);
    }

    @Test
    public void s05_enterWrongPass() {
        enterWrongPassTest(invalidPassword);
    }

    @Test
    public void s06_enterCorrectPass() {
        enterCorrectPassTest(validPassword);
    }

}
