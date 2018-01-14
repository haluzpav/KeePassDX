package eu.haluzpav.fetests.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import eu.haluzpav.fetests.MyDriver;

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
