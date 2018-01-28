package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import eu.haluzpav.fetests.model.toolbar.Toolbar;

public class GroupScreen extends BaseScreen {

    @FindBy(id = "group_list")
    private WebElement itemsList;

    @FindBy(id = "add_button")
    private WebElement addButton;

    @FindBy(id = "add_entry")
    private WebElement addEntryButton;

    @FindBy(id = "add_group")
    private WebElement addGroupButton;

    @Override
    protected List<WebElement> uniqueElements() {
        return Arrays.asList(itemsList, addButton);
    }

    @Override
    protected Toolbar createToolbar() {
        return new Toolbar() {
            @FindBy(id = "icon")
            private WebElement icon;

            @FindBy(id = "group_name")
            private WebElement title;

            @Override
            protected WebElement getIconElement() {
                return icon;
            }

            @Override
            protected WebElement getTitleElement() {
                return title;
            }
        };
    }

    public void clickFirstGroup() {
        itemsList.findElement(By.id("group_text")).click();
    }

    public void clickFirstEntry() {
        itemsList.findElement(By.id("entry_text")).click();
    }
}
