package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import io.appium.java_client.android.AndroidElement;

public class CreateDbPassDialog extends BaseDialog {

    // TODO remove language dependency
    private static final String VISIBILITY_BUTTON_XPATH = "//android.widget.ImageButton[@content-desc=\"Toggle password visibility\"]";

    @FindBy(id = "pass_password")
    private WebElement passwordField;

    @FindBy(id = "pass_conf_password")
    private WebElement passwordConfirmField;

    @Override
    protected List<WebElement> uniqueElements() {
        return Arrays.asList(passwordField, passwordConfirmField);
    }

    public void enterPassword(String pass, String passConfirm) {
        clickPassVisibilitySwitches();
        fillField(passwordField, pass);
        fillField(passwordConfirmField, passConfirm);
        clickPassVisibilitySwitches();
        driver().hideKeyboard();
    }

    private void clickPassVisibilitySwitches() {
        for (AndroidElement visSwitch : driver().findElements(By.xpath(VISIBILITY_BUTTON_XPATH))) {
            visSwitch.click();
        }
    }
}
