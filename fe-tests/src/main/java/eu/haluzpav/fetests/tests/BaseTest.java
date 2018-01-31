package eu.haluzpav.fetests.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import eu.haluzpav.fetests.Driven;
import eu.haluzpav.fetests.MyDriver;
import eu.haluzpav.fetests.model.BasePage;
import eu.haluzpav.fetests.model.Entry;
import eu.haluzpav.fetests.model.dialogs.AddGroupDialog;
import eu.haluzpav.fetests.model.dialogs.CreateDbPassDialog;
import eu.haluzpav.fetests.model.dialogs.CreateDbPathDialog;
import eu.haluzpav.fetests.model.dialogs.PassGeneratorDialog;
import eu.haluzpav.fetests.model.screens.EditEntryScreen;
import eu.haluzpav.fetests.model.screens.EnterDbPassScreen;
import eu.haluzpav.fetests.model.screens.GroupScreen;
import eu.haluzpav.fetests.model.screens.OpenDbScreen;
import eu.haluzpav.fetests.model.screens.ReadEntryScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class BaseTest implements Driven {

    private static final String APPIUM_PROPERTIES_PATH = "/fe-tests/src/main/resources/appium.properties";
    protected static OpenDbScreen openDbScreen;
    protected static EnterDbPassScreen enterDbPassScreen;
    protected static CreateDbPathDialog createDbPathDialog;
    protected static CreateDbPassDialog createDbPassDialog;
    protected static GroupScreen groupScreen;
    protected static ReadEntryScreen readEntryScreen;
    protected static EditEntryScreen editEntryScreen;
    protected static AddGroupDialog addGroupDialog;
    protected static PassGeneratorDialog passGeneratorDialog;
    private static Properties appiumProps;
    private static AppiumDriverLocalService service;
    private static MyDriver driver;

    @BeforeClass
    public static void setUp() {
        loadProperties();
        startService();
        initDriver(service.getUrl());
        BasePage.setDriver(driver);
    }

    private static void loadProperties() {
        File propertiesFile = new File(System.getProperty("user.dir") + APPIUM_PROPERTIES_PATH);
        appiumProps = new Properties();
        try {
            appiumProps.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startService() {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumProps.getProperty("mainJs")))
                .build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
    }

    private static DesiredCapabilities buildCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String capabilityName : appiumProps.getProperty("usedCapabilities").split(",")) {
            capabilities.setCapability(capabilityName, appiumProps.getProperty(capabilityName));
        }
        return capabilities;
    }

    private static void initDriver(URL url) {
        driver = new MyDriver(url, buildCapabilities());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

    public static String adb() {
        return appiumProps.getProperty("adb");
    }

    private static boolean isOnOpenDb() {
        if (openDbScreen == null) openDbScreen = new OpenDbScreen();
        return openDbScreen.isOpened();
    }

    private static void openDatabase(String path) {
        boolean entered = openDbScreen.enterDbPath(path);
        if (!path.isEmpty()) Assert.assertTrue(entered);

        openDbScreen.openDbFromPath();
    }

    protected static void enterCorrectPathTest(String path) {
        openDatabase(path);

        Assert.assertFalse(isOnOpenDb());
        Assert.assertTrue(isOnEnterPass());
    }

    protected static void tryGoToOpenDbScreenTest() {
        if (isOnEnterPass()) {
            enterDbPassScreen.toolbar().goBack();
        }
        Assert.assertFalse(isOnEnterPass());
        Assert.assertTrue(isOnOpenDb());
    }

    protected static boolean isOnEnterPass() {
        if (enterDbPassScreen == null) enterDbPassScreen = new EnterDbPassScreen();
        return enterDbPassScreen.isOpened();
    }

    private static void enterPassAndConfirm(String pass) {
        boolean entered = enterDbPassScreen.enterPassword(pass);
        if (!pass.isEmpty()) Assert.assertTrue(entered);

        enterDbPassScreen.confirm();
    }

    protected static void enterCorrectPassTest(String pass) {
        enterPassAndConfirm(pass);

        Assert.assertFalse(isOnEnterPass());
        Assert.assertTrue(isOnGroup());
    }

    protected static boolean isOnGroup() {
        if (groupScreen == null) groupScreen = new GroupScreen();
        return groupScreen.isOpened();
    }

    public static void openAddEntryTest() {
        Assert.assertTrue(groupScreen.isOpened());
        groupScreen.openAddEntry();
        editEntryScreen = new EditEntryScreen();
        Assert.assertTrue(editEntryScreen.isOpened());
    }

    public static void openPassGenTest() {
        Assert.assertTrue(editEntryScreen.isOpened());
        editEntryScreen.openGenerator();
        passGeneratorDialog = new PassGeneratorDialog();
        Assert.assertTrue(passGeneratorDialog.isOpened());
    }

    @Override
    public final MyDriver driver() {
        return driver;
    }

    protected void isAppOpenedTest() {
        // TODO isAppOpenedTest
        Assert.fail("TODO isAppOpenedTest");
    }

    protected void enterWrongPathTest(String path) {
        openDatabase(path);

        Assert.assertTrue(isOnOpenDb());
        Assert.assertFalse(isOnEnterPass());
    }

    protected void chooseFromListTest(int index) {
        openDbScreen.openRecent(index);

        Assert.assertFalse(isOnOpenDb());
        Assert.assertTrue(isOnEnterPass());
    }

    protected boolean isOnDefault() {
        return isOnEnterPass();
    }

    protected void databaseExistsTest() {
        // TODO databaseExistsTest
        Assert.fail("TODO databaseExistsTest");
    }

    protected void enterWrongPassTest(String pass) {
        enterPassAndConfirm(pass);

        Assert.assertTrue(isOnEnterPass());
        Assert.assertFalse(isOnGroup());
    }

    protected void enterCreatePath(String root, String filename) {
        createDbPathDialog.enterPath(root);
        createDbPathDialog.enterFilename(filename);
        createDbPathDialog.confirm();
    }

    public void enterCreatePass(String pass, String confirmPass) {
        createDbPassDialog.enterPassword(pass, confirmPass);
        createDbPassDialog.confirm();
    }

    public void openReadEntryTest(Entry entry) {
        Assert.assertTrue(groupScreen.isOpened());
        groupScreen.openEntry(entry);
        readEntryScreen = new ReadEntryScreen();
        Assert.assertTrue(readEntryScreen.isOpened());
    }

    public void openEditEntryTest() {
        Assert.assertTrue(readEntryScreen.isOpened());
        readEntryScreen.openEdit();
        editEntryScreen = new EditEntryScreen();
        Assert.assertTrue(editEntryScreen.isOpened());
    }

}
