package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassGeneratorDialog extends BaseDialog {

    // TODO slider

    private static final String[] switchIds = new String[]{
            "cb_uppercase",
            "cb_lowercase",
            "cb_digits",
            "cb_minus",
            "cb_underline",
            "cb_space",
            "cb_specials",
            "cb_brackets",
    };
    @FindBy(id = "password")
    private WebElement passField;
    @FindBy(id = "generate_password_button")
    private WebElement genButton;
    @FindBy(id = "length")
    private WebElement lengthField;
    // appium can't read switch states
    // these are default states :/
    private boolean[] switchStates = new boolean[]{
            true, true, true, false, false, false, false, false,
    };

    public void setSwitchStates(boolean[] states) {
        if (states.length != switchStates.length) throw new IllegalStateException();

        for (int i = 0; i < states.length; i++) {
            if (states[i] == switchStates[i]) continue;
            driver().findElement(By.id(switchIds[i])).click();
            switchStates[i] = !switchStates[i];
        }
    }

    public boolean setLength(int length) {
        lengthField.sendKeys(String.valueOf(length));
        return length == Integer.valueOf(lengthField.getText());
    }

    public String generatePass() {
        genButton.click();
        return passField.getText();
    }
}