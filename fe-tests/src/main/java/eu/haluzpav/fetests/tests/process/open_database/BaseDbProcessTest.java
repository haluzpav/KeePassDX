package eu.haluzpav.fetests.tests.process.open_database;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.haluzpav.fetests.model.screens.EnterDbPassScreen;
import eu.haluzpav.fetests.model.screens.GroupScreen;
import eu.haluzpav.fetests.model.screens.OpenDbScreen;
import eu.haluzpav.fetests.tests.BaseTest;

public abstract class BaseDbProcessTest extends BaseTest {

    protected static OpenDbScreen openDbScreen;
    protected static EnterDbPassScreen enterDbPassScreen;
    protected static GroupScreen groupScreen;
    protected final String databasePath = "/storage/emulated/0/keepass/keepass.kdbx";
    protected final String validPassword = "a";
    protected final List<String> invalidPasswords = Collections.unmodifiableList(Arrays.asList(
            "Trautenberg666", "4 8 15 16 23 42", "bananas?"));

    protected void isAppOpenedTest() {
        // TODO
    }

    private boolean isOnOpenDb() {
        if (openDbScreen == null) openDbScreen = new OpenDbScreen();
        return openDbScreen.isOpened();
    }

    private void openDatabase(String path) {
        boolean entered = openDbScreen.enterDbPath(path);
        Assert.assertTrue(entered);

        openDbScreen.openDbFromPath();
    }

    public void enterWrongPathTest(String path) {
        openDatabase(path);

        Assert.assertTrue(isOnOpenDb());
        Assert.assertFalse(isOnEnterPass());
    }

    public void enterCorrectPathTest(String path) {
        openDatabase(path);

        Assert.assertFalse(isOnOpenDb());
        Assert.assertTrue(isOnEnterPass());
    }

    protected boolean isOnDefault() {
        if (enterDbPassScreen == null) enterDbPassScreen = new EnterDbPassScreen();
        return enterDbPassScreen.isOpened();
    }

    protected boolean databaseExists() {
        // TODO always exists?
        return true;
    }

    protected boolean isOnEnterPass() {
        if (enterDbPassScreen == null) enterDbPassScreen = new EnterDbPassScreen();
        return enterDbPassScreen.isOpened();
    }

    private void enterPassAndConfirm(String pass) {
        boolean entered = enterDbPassScreen.enterPassword(pass);
        Assert.assertTrue(entered);

        enterDbPassScreen.confirm();
    }

    protected void enterWrongPassTest(String pass) {
        enterPassAndConfirm(pass);

        Assert.assertTrue(isOnEnterPass());
        Assert.assertFalse(isOnGroup());
    }

    protected void enterCorrectPassTest(String pass) {
        enterPassAndConfirm(pass);

        Assert.assertFalse(isOnEnterPass());
        Assert.assertTrue(isOnGroup());
    }

    protected boolean isOnGroup() {
        if (groupScreen == null) groupScreen = new GroupScreen();
        return groupScreen.isOpened();
    }

}
