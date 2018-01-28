package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import eu.haluzpav.fetests.model.toolbar.ToolbarOption;

public class OpenDbScreen extends BaseScreen {

    @FindBy(id = "file_select")
    private WebElement fileSelectContainer;

    @FindBy(id = "browse_button")
    private WebElement fileBrowserButton;

    @FindBy(id = "open_database")
    private WebElement openDatabaseButton;

    @FindBy(id = "file_list")
    private WebElement recentDatabasesList;

    @FindBy(id = "create_database")
    private WebElement createDatabaseButton;

    @Override
    protected List<WebElement> uniqueElements() {
        return Arrays.asList(fileSelectContainer, openDatabaseButton, createDatabaseButton);
    }

    private WebElement getPathField() {
        return fileSelectContainer.findElement(By.className(CLASS_FIELD_EDIT));
    }

    public boolean enterDbPath(String path) {
        getPathField().clear();
        getPathField().sendKeys(path);
        return !getPathField().getText().isEmpty();
    }

    public void openDbFromPath() {
        openDatabaseButton.click();
    }

    public void openRecent(int index) {
        int i = 0;
        for (WebElement element : recentDatabasesList.findElements(By.className(CLASS_FIELD))) {
            if (i++ < index) continue;
            element.click();
            return;
        }
        throw new NoSuchElementException("index " + i + "bigger than number of recent databases");
    }

    public void openCreateDatabase() {
        createDatabaseButton.click();
    }

    public void openDonate() {
        toolbar().click(ToolbarOption.DONATE);
    }

}
