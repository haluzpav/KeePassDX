package eu.haluzpav.fetests.tests;

import org.junit.AfterClass;
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
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class BaseTest implements Driven {

    private static final String APPIUM_PROPERTIES_PATH = "/fe-tests/src/main/resources/appium.properties";

    private Properties appiumProps;
    private AppiumDriverLocalService service;
    private MyDriver driver;

    @BeforeClass
    public void setUp() {
        loadProperties();
        startService();
        initDriver(service.getUrl());
        BasePage.setDriver(driver());
    }

    private void loadProperties() {
        File propertiesFile = new File(System.getProperty("user.dir") + APPIUM_PROPERTIES_PATH);
        appiumProps = new Properties();
        try {
            appiumProps.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startService() {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumProps.getProperty("mainJs")))
                .build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
    }

    private DesiredCapabilities buildCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String capabilityName : appiumProps.getProperty("usedCapabilities").split(",")) {
            capabilities.setCapability(capabilityName, appiumProps.getProperty(capabilityName));
        }
        return capabilities;
    }

    private void initDriver(URL url) {
        driver = new MyDriver(url, buildCapabilities());
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

    @Override
    public final MyDriver driver() {
        return driver;
    }

}
