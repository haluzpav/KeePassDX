package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void openFirstRecent() {
        recentDatabasesList.findElement(By.className(CLASS_FIELD)).click();
    }

    public void openCreateDatabase() {
        createDatabaseButton.click();
    }

    public void openDonate() {
        toolbar().click(ToolbarOption.DONATE);
    }

}
