package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateDbPassScreen extends BaseDialog {

    @FindBy(id = "pass_password")
    private WebElement passwordField;

    @FindBy(id = "pass_conf_password")
    private WebElement passwordConfirmField;

    public void enterPassword(String pass, String passConfirm) {
        fillField(passwordField, pass);
        fillField(passwordConfirmField, passConfirm);
    }
}
