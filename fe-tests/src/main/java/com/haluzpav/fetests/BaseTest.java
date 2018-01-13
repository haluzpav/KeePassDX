package com.haluzpav.fetests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

abstract class BaseTest {

    private static final String PROPERTIES_PATH = "/fe-tests/src/main/resources/appium.properties";

    private Properties properties;
    private AppiumDriverLocalService service;
    private AppiumDriver<WebElement> driver;

    @Before
    public void setUp() throws IOException {
        loadProperties();
        startService();
        initDriver(service.getUrl());
    }

    //@Before
    public void setUpFromRunning() throws IOException {
        // does not work
        loadProperties();
        initDriver(new URL(properties.getProperty("url")));
    }

    private void loadProperties() {
        File propertiesFile = new File(System.getProperty("user.dir") + PROPERTIES_PATH);
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startService() {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/lib/node_modules/appium/build/lib/main.js"))
                .build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
    }

    private DesiredCapabilities buildCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String capabilityName : properties.getProperty("capabilities").split(",")) {
            capabilities.setCapability(capabilityName, properties.getProperty(capabilityName));
        }
        return capabilities;
    }

    private void initDriver(URL url) {
        driver = new AndroidDriver<>(url, buildCapabilities());
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

    final AppiumDriver<WebElement> driver() {
        return driver;
    }

}
