package eu.haluzpav.fetests.tests.process.open_database.TDL2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Collection;

import eu.haluzpav.fetests.model.dialogs.BaseDialog;
import eu.haluzpav.fetests.model.dialogs.CreateDbPassDialog;
import eu.haluzpav.fetests.model.dialogs.CreateDbPathDialog;
import eu.haluzpav.fetests.model.screens.OpenDbScreen;
import eu.haluzpav.fetests.tests.process.open_database.BaseDbProcessTest;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.T5_Data;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T5_NewWrongPathWrongPassFromList extends BaseDbProcessTest {

    @Parameterized.Parameter(0)
    public String wrongDbPathRoot1;

    @Parameterized.Parameter(1)
    public String wrongDbPathFilename1;

    @Parameterized.Parameter(2)
    public String wrongDbPathRoot2;

    @Parameterized.Parameter(3)
    public String wrongDbPathFilename2;

    @Parameterized.Parameter(4)
    public String correctDbPathRoot;

    @Parameterized.Parameter(5)
    public String correctDbPathFilename;

    @Parameterized.Parameter(6)
    public String invalidPassword1;

    @Parameterized.Parameter(7)
    public String invalidConfirmPassword1;

    @Parameterized.Parameter(8)
    public String invalidPassword2;

    @Parameterized.Parameter(9)
    public String invalidConfirmPassword2;

    @Parameterized.Parameter(10)
    public String validPassword;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return new T5_Data().data();
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
    public void s02_databaseNotExists() {
        // TODO s02_databaseNotExists
        Assert.fail("TODO s02_databaseNotExists");
    }

    @Test
    public void s03_openCreate() {
        openDbScreen.openCreateDatabase();

        createDbPathDialog = new CreateDbPathDialog();

        Assert.assertTrue(createDbPathDialog.isOpened());
    }

    @Test
    public void s04_createWrongPath1() {
        enterCreatePath(wrongDbPathRoot1, wrongDbPathFilename1);

        Assert.assertTrue(createDbPathDialog.isOpened());
    }

    @Test
    public void s05_createWrongPath2() {
        enterCreatePath(wrongDbPathRoot2, wrongDbPathFilename2);

        Assert.assertTrue(createDbPathDialog.isOpened());
    }

    @Test
    public void s06_createCorrectPath() {
        enterCreatePath(correctDbPathRoot, correctDbPathFilename);

        createDbPassDialog = new CreateDbPassDialog();

        Assert.assertFalse(createDbPathDialog.isOpened());
        Assert.assertTrue(createDbPassDialog.isOpened());
    }

    @Test
    public void s07_createInvalidPass1() {
        enterCreatePass(invalidPassword1, invalidConfirmPassword1);

        BaseDialog confirmEmptyDialog = new BaseDialog();
        if (confirmEmptyDialog.isOpened()) {
            confirmEmptyDialog.cancel();
            Assert.fail();
        }
        Assert.assertTrue(createDbPassDialog.isOpened());
    }

    @Test
    public void s08_createInvalidPass2() {
        enterCreatePass(invalidPassword2, invalidConfirmPassword2);

        BaseDialog confirmEmptyDialog = new BaseDialog();
        if (confirmEmptyDialog.isOpened()) {
            confirmEmptyDialog.cancel();
            Assert.fail();
        }
        Assert.assertTrue(createDbPassDialog.isOpened());
    }

    @Test
    public void s09_createValidPass() {
        enterCreatePass(validPassword, validPassword);

        BaseDialog confirmEmptyDialog = new BaseDialog();
        if (confirmEmptyDialog.isOpened()) {
            if (validPassword.isEmpty()) {
                confirmEmptyDialog.confirm();
            } else {
                confirmEmptyDialog.cancel();
                createDbPassDialog = new CreateDbPassDialog();
                createDbPassDialog.cancel(); // close for consistency
                Assert.fail();
            }
        }
        openDbScreen = new OpenDbScreen();
        Assert.assertFalse(createDbPassDialog.isOpened());
        Assert.assertTrue(openDbScreen.isOpened());
    }

    @Test
    public void s10_chooseFromList() {
        chooseFromListTest(0);

        // TODO correct db?
        Assert.fail("TODO correct db?");
    }

    @Test
    public void s11_enterPass() {
        enterCorrectPassTest(validPassword);
    }

}
