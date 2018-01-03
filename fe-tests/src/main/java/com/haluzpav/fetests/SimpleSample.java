package com.haluzpav.fetests;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import static org.junit.Assert.*;

public class SimpleSample {

    public AppiumDriver<WebElement> driver;
    private static AppiumDriverLocalService service;

    @Before
    public void setUp() throws IOException {
//        service = AppiumDriverLocalService.buildDefaultService();
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/lib/node_modules/appium/build/lib/main.js"))
                .build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
//        File app = new File(appDir.getCanonicalPath(), "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", "/home/pavel/gitjects/keepassdx/app/build/outputs/apk/libre/debug/app-libre-debug.apk");
        capabilities.setCapability("appPackage", "com.kunzisoft.keepass.libre");
        capabilities.setCapability("appActivity", "com.kunzisoft.keepass.KeePass");
//        capabilities.setCapability("platformName", "Android");
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
//        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723"), capabilities);
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

    @Test
    public void apiDemo() {
        assertTrue(true); // hey, this testing is easy!
//        WebElement el = driver.findElement(By.xpath(".//*[@text='Animation']"));
//        assertEquals("Animation", el.getText());
//        el = driver.findElementByClassName("android.widget.TextView");
//        assertEquals("API Demos", el.getText());
//        el = driver.findElement(By.xpath(".//*[@text='App']"));
//        el.click();
//        List<WebElement> els = driver.findElementsByClassName("android.widget.TextView");
//        assertEquals("Activity", els.get(2).getText());
    }
}
