package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Collection;

import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.T2_Data;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T2_WrongPathWrongPass extends BaseDbProcessTest {

    @Parameterized.Parameter(0)
    public String firstWrongPath;

    @Parameterized.Parameter(1)
    public String secondWrongPath;

    @Parameterized.Parameter(2)
    public String correctPath;

    @Parameterized.Parameter(3)
    public String invalidPassword;

    @Parameterized.Parameter(4)
    public String validPassword;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return new T2_Data().data();
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
        enterWrongPathTest(firstWrongPath);
    }

    @Test
    public void s06_enterWrongPath() {
        enterWrongPathTest(secondWrongPath);
    }

    @Test
    public void s07_enterCorrectPath() {
        enterCorrectPathTest(correctPath);
    }

    @Test
    public void s08_enterWrongPass() {
        enterWrongPassTest(invalidPassword);
    }

    @Test
    public void s09_enterCorrectPass() {
        enterCorrectPassTest(validPassword);
    }

}
