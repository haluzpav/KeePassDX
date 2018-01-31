package eu.haluzpav.fetests.model.dialogs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import eu.haluzpav.fetests.model.BasePage;

public class ListDialog extends BaseDialog {

    @FindBy(id = "select_dialog_listview")
    private WebElement list;

    public void clickListItem(int pos) {
        list.findElements(By.className(BasePage.CLASS_FIELD)).get(pos).click();
    }

    @Override
    protected List<WebElement> uniqueElements() {
        return Arrays.asList(list);
    }
}
