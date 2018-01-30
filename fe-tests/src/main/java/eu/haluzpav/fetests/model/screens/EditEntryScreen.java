package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import eu.haluzpav.fetests.model.Entry;

public class EditEntryScreen extends BaseScreen {

    // TODO advanced fields

    @FindBy(id = "entry_title")
    private WebElement titleField;

    @FindBy(id = "icon_button")
    private WebElement iconButton;

    @FindBy(id = "entry_user_name")
    private WebElement usernameField;

    @FindBy(id = "entry_url")
    private WebElement urlField;

    @FindBy(id = "entry_password")
    private WebElement passField;

    @FindBy(xpath = "(//android.widget.ImageButton[@content-desc=\"Toggle password visibility\"])[1]")
    private WebElement togglePassButton;

    @FindBy(id = "entry_confpassword")
    private WebElement confPassField;

    @FindBy(xpath = "(//android.widget.ImageButton[@content-desc=\"Toggle password visibility\"])[2]")
    private WebElement toggleConfPassButton;

    @FindBy(id = "generate_button")
    private WebElement generateButton;

    @FindBy(id = "entry_comment")
    private WebElement commentField;

    @FindBy(id = "add_advanced")
    private WebElement addAdvancedButton;

    @FindBy(id = "entry_save")
    private WebElement saveButton;

    private void fillNotNull(WebElement element, String value) {
        if (value == null) return;
        fillField(element, value);
    }

    public void fillValues(Entry entry) {
        fillNotNull(titleField, entry.title);
        fillNotNull(usernameField, entry.username);
        fillNotNull(urlField, entry.url);
        togglePassButton.click();
        fillNotNull(passField, entry.pass);
        togglePassButton.click();
        toggleConfPassButton.click();
        fillNotNull(confPassField, entry.confPass);
        toggleConfPassButton.click();
        fillNotNull(commentField, entry.comments);
    }

    public void openGenerator() {
        generateButton.click();
    }

    public void save() {
        saveButton.click();
    }

}
