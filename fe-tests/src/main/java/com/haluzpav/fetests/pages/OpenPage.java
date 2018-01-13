package com.haluzpav.fetests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;

public class OpenPage extends BasePage {

    @FindBy(id = "menu_donate")
    WebElement donateButton;

    public OpenPage(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    public void clickDonate() {
        donateButton.click();
    }

}
