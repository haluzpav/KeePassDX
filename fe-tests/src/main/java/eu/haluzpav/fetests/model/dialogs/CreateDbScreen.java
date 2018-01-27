package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateDbScreen extends BaseDialog {

    @FindBy(id = "folder_path")
    private WebElement folderField;

    @FindBy(id = "browse_button")
    private WebElement browserButton;

    @FindBy(id = "filename")
    private WebElement filenameField;

    @FindBy(id = "file_types")
    private WebElement fileTypeSelector;

    public void enterPath(String path) {
        fillField(folderField, path);
    }

    public void enterFilename(String filename) {
        fillField(filenameField, filename);
    }

}
