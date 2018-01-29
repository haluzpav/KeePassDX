package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import eu.haluzpav.fetests.model.BasePage;


public class BaseDialog extends BasePage {

    @FindBy(id = "button1")
    private WebElement confirmButton;

    @FindBy(id = "button2")
    private WebElement cancelButton;

    @Override
    protected List<WebElement> uniqueElements() {
        // TODO better unique
        return Arrays.asList(confirmButton, cancelButton);
    }

    public void confirm() {
        confirmButton.click();
    }

    public void cancel() {
        cancelButton.click();
    }

}
