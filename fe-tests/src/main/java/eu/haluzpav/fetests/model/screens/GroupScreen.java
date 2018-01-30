package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import eu.haluzpav.fetests.model.toolbar.Toolbar;

public class GroupScreen extends BaseScreen {

    // keeps state of menu of all possible child screens
    private static Deque<Boolean> isAddMenuOpened = new ArrayDeque<>();

    @FindBy(id = "group_list")
    private WebElement itemsList;

    @FindBy(id = "add_button")
    private WebElement addButton;

    @FindBy(id = "add_entry")
    private WebElement addEntryButton;

    @FindBy(id = "add_group")
    private WebElement addGroupButton;

    public GroupScreen() {
        super();
        isAddMenuOpened.push(false);
    }

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

            @Override
            public void goBack() {
                super.goBack();
                isAddMenuOpened.pop();
            }
        };
    }

    public void clickFirstGroup() {
        itemsList.findElement(By.id("group_text")).click();
    }

    public void clickFirstEntry() {
        itemsList.findElement(By.id("entry_text")).click();
    }

    private void changeAddMenuState() {
        addButton.click();
        boolean previous = isAddMenuOpened.pop();
        isAddMenuOpened.push(!previous);
    }

    private void openAddMenu() {
        if (isAddMenuOpened.peekFirst()) return;
        changeAddMenuState();
    }

    public void openAddGroup() {
        openAddMenu();
        addGroupButton.click();
    }

    public void openAddEntry() {
        openAddMenu();
        addEntryButton.click();
    }
}
