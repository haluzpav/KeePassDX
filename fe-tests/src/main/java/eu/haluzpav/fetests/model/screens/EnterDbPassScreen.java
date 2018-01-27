package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EnterDbPassScreen extends BaseScreen {

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "pass_keyfile")
    private WebElement keyfileField;

    @FindBy(id = "browse_button")
    private WebElement browserButton;

    @FindBy(id = "default_database")
    private WebElement defaultDbSwitch;

    @FindBy(id = "pass_ok")
    private WebElement confirmButton;

    public void enterPassword(String pass) {
        fillField(passwordField, pass);
    }

    public void setAsDefault() {
        defaultDbSwitch.click();
    }

    public void confirm() {
        confirmButton.click();
    }

}
