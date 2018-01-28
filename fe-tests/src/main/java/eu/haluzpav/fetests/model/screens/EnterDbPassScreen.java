package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import eu.haluzpav.fetests.model.toolbar.Toolbar;

public class EnterDbPassScreen extends BaseScreen {

    // TODO remove language dependency
    private static final String BACK_XPATH = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";

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

    @Override
    protected Toolbar createToolbar() {
        return new Toolbar() {
            @Override
            protected WebElement getBackElement() {
                return toolbarContainer.findElement(By.xpath(BACK_XPATH));
            }
        };
    }
}
