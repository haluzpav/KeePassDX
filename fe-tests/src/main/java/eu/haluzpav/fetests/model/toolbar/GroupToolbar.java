package eu.haluzpav.fetests.model.toolbar;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GroupToolbar extends Toolbar {

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

}
