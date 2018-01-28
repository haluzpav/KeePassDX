package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Collection;

import eu.haluzpav.fetests.model.toolbar.ToolbarOption;
import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.T1_Data;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_DefaultWrongPass extends BaseDbProcessTest {

    @Parameterized.Parameter(0)
    public String firstInvalidPassword;

    @Parameterized.Parameter(1)
    public String secondInvalidPassword;

    @Parameterized.Parameter(2)
    public String validPassword;

    @Parameterized.Parameters(name = "{index}: \"{0}\", \"{1}\", \"{2}\"")
    public static Collection<Object[]> data() {
        return new T1_Data().data();
    }

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
        enterWrongPassTest(firstInvalidPassword);
    }

    @Test
    public void s03_enterWrongPass() {
        enterWrongPassTest(secondInvalidPassword);
    }

    @Test
    public void s04_enterCorrectPass() {
        enterCorrectPassTest(validPassword);
    }

    @Test
    public void s99_goToStart() {
        // need to go back manually because Parametrized runner does not restart app
        groupScreen.toolbar().click(ToolbarOption.LOCK_DB);
        Assert.assertTrue(isOnEnterPass());
    }

}
