package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddGroupDialog extends BaseDialog {

    @FindBy(id = "group_name")
    private WebElement groupName;

    @FindBy(id = "icon_button")
    private WebElement iconButton;

    public void enterNameAndConfirm(String name) {
        fillField(groupName, name);
        confirm();
    }

}
