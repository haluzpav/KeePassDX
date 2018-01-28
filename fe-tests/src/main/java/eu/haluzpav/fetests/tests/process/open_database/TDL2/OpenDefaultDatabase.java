package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import eu.haluzpav.fetests.model.screens.EnterDbPassScreen;
import eu.haluzpav.fetests.model.screens.GroupScreen;
import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OpenDefaultDatabase extends BaseDbProcessTest {

    private static EnterDbPassScreen enterDbPassScreen;
    private static GroupScreen groupScreen;

    @Test
    public void s00_isOnDefault() {
        enterDbPassScreen = new EnterDbPassScreen();

        Assert.assertTrue(enterDbPassScreen.isOpened());

        // TODO create DB
    }

    @Test
    public void s01_enterPass() {
        boolean entered = enterDbPassScreen.enterPassword(validPassword);

        Assert.assertTrue(entered);
    }

    @Test
    public void s02_isOpened() {
        enterDbPassScreen.confirm();

        groupScreen = new GroupScreen();

        Assert.assertTrue(groupScreen.isOpened());
    }

}
