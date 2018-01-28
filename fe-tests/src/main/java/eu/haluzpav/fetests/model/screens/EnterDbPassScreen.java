package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class EnterDbPassScreen extends BaseScreen {

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "text_input_password_toggle")
    private WebElement passwordToggle;

    @FindBy(id = "pass_keyfile")
    private WebElement keyfileField;

    @FindBy(id = "browse_button")
    private WebElement browserButton;

    @FindBy(id = "default_database")
    private WebElement defaultDbSwitch;

    @FindBy(id = "pass_ok")
    private WebElement confirmButton;

    public boolean enterPassword(String pass) {
        // need to toggle password visibility to be able to type and check it
        passwordToggle.click();
        fillField(passwordField, pass);
        boolean success = !passwordField.getText().isEmpty();
        passwordToggle.click();
        return success;
    }

    public void switchDefault() {
        // TODO how to detect state?
        defaultDbSwitch.click();
    }

    public void confirm() {
        confirmButton.click();
    }

    @Override
    protected List<WebElement> uniqueElements() {
        return Arrays.asList(passwordField, defaultDbSwitch, confirmButton);
    }
}
