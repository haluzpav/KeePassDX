package com.haluzpav.fetests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

abstract class BasePage {

    private AppiumDriver<WebElement> driver;

    BasePage(AppiumDriver<WebElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    final AppiumDriver<WebElement> driver() {
        return driver;
    }

}
