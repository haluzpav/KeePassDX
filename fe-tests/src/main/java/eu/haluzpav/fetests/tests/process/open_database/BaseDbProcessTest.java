package eu.haluzpav.fetests.tests.process.open_database;

import org.junit.Assert;
import org.junit.Test;

import eu.haluzpav.fetests.model.dialogs.CreateDbPassScreen;
import eu.haluzpav.fetests.model.dialogs.CreateDbPathScreen;
import eu.haluzpav.fetests.model.screens.EnterDbPassScreen;
import eu.haluzpav.fetests.model.screens.GroupScreen;
import eu.haluzpav.fetests.model.screens.OpenDbScreen;
import eu.haluzpav.fetests.model.toolbar.ToolbarOption;
import eu.haluzpav.fetests.tests.BaseTest;

public abstract class BaseDbProcessTest extends BaseTest {

    protected static OpenDbScreen openDbScreen;
    protected static EnterDbPassScreen enterDbPassScreen;
    protected static CreateDbPathScreen createDbPathScreen;
    protected static CreateDbPassScreen createDbPassScreen;
    protected static GroupScreen groupScreen;

    protected void isAppOpenedTest() {
        // TODO isAppOpenedTest
        Assert.fail("TODO isAppOpenedTest");
    }

    private boolean isOnOpenDb() {
        if (openDbScreen == null) openDbScreen = new OpenDbScreen();
        return openDbScreen.isOpened();
    }

    private void openDatabase(String path) {
        boolean entered = openDbScreen.enterDbPath(path);
        if (!path.isEmpty()) Assert.assertTrue(entered);

        openDbScreen.openDbFromPath();
    }

    protected void enterWrongPathTest(String path) {
        openDatabase(path);

        Assert.assertTrue(isOnOpenDb());
        Assert.assertFalse(isOnEnterPass());
    }

    protected void enterCorrectPathTest(String path) {
        openDatabase(path);

        Assert.assertFalse(isOnOpenDb());
        Assert.assertTrue(isOnEnterPass());
    }

    protected void chooseFromListTest(int index) {
        openDbScreen.openRecent(index);

        Assert.assertFalse(isOnOpenDb());
        Assert.assertTrue(isOnEnterPass());
    }

    protected boolean isOnDefault() {
        return isOnEnterPass();
    }

    protected void tryGoToOpenDbScreenTest() {
        if (isOnEnterPass()) {
            enterDbPassScreen.toolbar().goBack();
        }
        Assert.assertFalse(isOnEnterPass());
        Assert.assertTrue(isOnOpenDb());
    }

    protected void databaseExistsTest() {
        // TODO databaseExistsTest
        Assert.fail("TODO databaseExistsTest");
    }

    protected boolean isOnEnterPass() {
        if (enterDbPassScreen == null) enterDbPassScreen = new EnterDbPassScreen();
        return enterDbPassScreen.isOpened();
    }

    private void enterPassAndConfirm(String pass) {
        boolean entered = enterDbPassScreen.enterPassword(pass);
        if (!pass.isEmpty()) Assert.assertTrue(entered);

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

    protected void enterCreatePath(String root, String filename) {
        createDbPathScreen.enterPath(root);
        createDbPathScreen.enterFilename(filename);
        createDbPathScreen.confirm();
    }

    public void enterCreatePass(String pass, String confirmPass) {
        createDbPassScreen.enterPassword(pass, confirmPass);
        createDbPassScreen.confirm();
    }

    protected boolean isOnGroup() {
        if (groupScreen == null) groupScreen = new GroupScreen();
        return groupScreen.isOpened();
    }

    @Test
    public void s90_goToStart() {
        // need to go back manually because Parametrized runner does not restart app
        groupScreen.toolbar().click(ToolbarOption.LOCK_DB);
        Assert.assertTrue(isOnEnterPass());
    }

}
