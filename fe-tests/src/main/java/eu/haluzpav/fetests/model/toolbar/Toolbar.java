package eu.haluzpav.fetests.model.toolbar;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import eu.haluzpav.fetests.model.BasePage;

public class Toolbar extends BasePage {

    // TODO remove language dependency
    private static final String MORE_OPTIONS_XPATH = "//android.widget.ImageView[@content-desc=\"More options\"]";

    @FindBy(id = "toolbar")
    private WebElement toolbar;

    public void click(ToolbarOption option) {
        try {
            toolbar.findElement(By.id(option.id)).click();
            return;
        } catch (NoSuchElementException e) {
            // pass
        }
        toolbar.findElement(By.xpath(MORE_OPTIONS_XPATH)).click();
        for (WebElement textElement : driver().findElements(By.className(CLASS_FIELD))) {
            if (!textElement.getText().toLowerCase().contains(option.text)) continue;
            textElement.click();
            return;
        }
    }

    protected WebElement getIconElement() {
        throw new NoSuchElementException("icon not defined");
    }

    protected WebElement getTitleElement() {
        return toolbar.findElement(By.className(CLASS_FIELD));
    }

    public boolean isIconPresent() {
        try {
            return getIconElement().getSize().getHeight() > 0 && getIconElement().getSize().getWidth() > 0;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTitle() {
        return getTitleElement().getText();
    }

}
