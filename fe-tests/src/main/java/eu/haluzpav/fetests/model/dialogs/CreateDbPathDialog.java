package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class CreateDbPathDialog extends BaseDialog {

    @FindBy(id = "folder_path")
    private WebElement folderField;

    @FindBy(id = "browse_button")
    private WebElement browserButton;

    @FindBy(id = "filename")
    private WebElement filenameField;

    @FindBy(id = "file_types")
    private WebElement fileTypeSelector;

    @Override
    protected List<WebElement> uniqueElements() {
        return Arrays.asList(folderField, filenameField);
    }

    public void enterPath(String path) {
        fillField(folderField, path);
    }

    public void enterFilename(String filename) {
        fillField(filenameField, filename);
    }

}
