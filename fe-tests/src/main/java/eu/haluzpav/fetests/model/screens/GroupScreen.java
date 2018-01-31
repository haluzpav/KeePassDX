package eu.haluzpav.fetests.model.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import eu.haluzpav.fetests.model.Entry;
import eu.haluzpav.fetests.model.dialogs.ListDialog;
import eu.haluzpav.fetests.model.toolbar.Toolbar;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;

public class GroupScreen extends BaseScreen {

    // TODO better screen stacking

    // keeps state of menu of all possible child screens
    private static final Deque<Boolean> isAddMenuOpened = new ArrayDeque<>();

    private static final String ENTRY_ID = "entry_text";
    // private static final String GROUP_ID = "group_text"; // groups have no id!

    private static final int POS_DELETE_IN_LIST_DIALOG = 1;

    @FindBy(id = "group_list")
    private WebElement itemsList;

    @FindBy(id = "add_button")
    private WebElement addButton;

    @FindBy(id = "add_entry")
    private WebElement addEntryButton;

    @FindBy(id = "add_group")
    private WebElement addGroupButton;

    public GroupScreen() {
        this(true);
        isAddMenuOpened.push(false);
    }

    /**
     * pushToStack=false only after going back!
     */
    public GroupScreen(boolean pushToStack) {
        super();
        if (pushToStack) isAddMenuOpened.push(false);
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

    public boolean hasEntry(Entry entry) {
        try {
            for (WebElement entryTitleElem : itemsList.findElements(By.id(ENTRY_ID))) {
                if (entryTitleElem.getText().equals(entry.title)) return true;
            }
        } catch (NoSuchElementException ignored) {

        }
        return false;
    }

    private void clickEntry(Entry entry) {
        for (WebElement entryTitleElem : itemsList.findElements(By.id(ENTRY_ID))) {
            if (entryTitleElem.getText().equals(entry.title)) entryTitleElem.click();
        }
    }

    private void longClick(Entry entry) {
        for (WebElement entryTitleElem : itemsList.findElements(By.id(ENTRY_ID))) {
            if (!entryTitleElem.getText().equals(entry.title)) continue;
            TouchAction action = new TouchAction(driver());
            action.longPress(ElementOption.element(entryTitleElem)).release().perform();
        }
    }

    public void openEntry(Entry entry) {
        if (hasEntry(entry)) clickEntry(entry);
        else throw new NoSuchElementException("");
    }

    public void deleteEntry(Entry entry) {
        if (hasEntry(entry)) {
            longClick(entry);
            new ListDialog().clickListItem(POS_DELETE_IN_LIST_DIALOG);
        } else throw new NoSuchElementException("");
    }

    private WebElement getGroupElement(String groupName) {
        for (WebElement element : itemsList.findElements(By.className(CLASS_FIELD))) {
            if (element.getText().equals(groupName)) return element;
        }
        throw new NoSuchElementException("");
    }

    public boolean hasGroup(String groupName) {
        try {
            getGroupElement(groupName);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void clickGroup(String groupName) {
        getGroupElement(groupName).click();
    }

    public void openGroup(String groupName) {
        if (hasGroup(groupName)) clickGroup(groupName);
        else throw new NoSuchElementException("");
    }
}
