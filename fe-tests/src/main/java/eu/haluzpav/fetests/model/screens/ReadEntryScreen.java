package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import eu.haluzpav.fetests.model.Entry;
import eu.haluzpav.fetests.model.toolbar.ToolbarOption;

public class ReadEntryScreen extends BaseScreen {

    // TODO advanced fields

    @FindBy(id = "entry_user_name")
    private WebElement usernameField;

    @FindBy(id = "entry_url")
    private WebElement urlField;

    @FindBy(id = "entry_password")
    private WebElement passField;

    @FindBy(id = "entry_comment")
    private WebElement commentField;

    @FindBy(id = "entry_created")
    private WebElement createdField;

    @FindBy(id = "entry_modified")
    private WebElement modifiedField;

    @FindBy(id = "entry_accessed")
    private WebElement accessedField;

    @FindBy(id = "entry_expires")
    private WebElement expiresField;

    @FindBy(id = "entry_edit")
    private WebElement editButton;

    public void openEdit() {
        editButton.click();
    }

    public boolean isSameAs(Entry entry) {
        return getEntry().equals(entry);
    }

    private Entry getEntry() {
        return new Entry() {{
            title = toolbar().getTitle();
            username = usernameField.getText();
            url = urlField.getText();

            toolbar().click(ToolbarOption.TOGGLE_PASS);
            pass = passField.getText();
            toolbar().click(ToolbarOption.TOGGLE_PASS);

            comments = commentField.getText();
            created = createdField.getText();
            modified = modifiedField.getText();
            accessed = accessedField.getText();
            expires = expiresField.getText();
        }};
    }
}
