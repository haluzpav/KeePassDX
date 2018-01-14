package com.haluzpav.fetests.pages;

import com.haluzpav.fetests.Driven;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

abstract class BasePage implements Driven {

    private AppiumDriver<WebElement> driver;

    BasePage(AppiumDriver<WebElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public final AppiumDriver<WebElement> driver() {
        return driver;
    }

}
