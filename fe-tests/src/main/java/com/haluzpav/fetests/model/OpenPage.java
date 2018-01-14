package com.haluzpav.fetests.model;

import com.haluzpav.fetests.MyDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenPage extends BasePage {

    @FindBy(id = "menu_donate")
    private WebElement donateButton;

    OpenPage(MyDriver driver) {
        super(driver);
    }

    public void clickDonate() {
        donateButton.click();
    }

}
