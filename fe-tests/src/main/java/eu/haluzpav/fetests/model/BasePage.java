package eu.haluzpav.fetests.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import eu.haluzpav.fetests.Driven;
import eu.haluzpav.fetests.MyDriver;

public class BasePage implements Driven {

    protected static final String CLASS_FIELD_EDIT = "android.widget.EditText";
    protected static final String CLASS_FIELD = "android.widget.TextView";

    private static MyDriver driver;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public static void setDriver(MyDriver driver) {
        BasePage.driver = driver;
    }

    @Override
    public final MyDriver driver() {
        return driver;
    }

    protected void fillField(WebElement field, String text) {
        field.clear();
        field.sendKeys(text);
    }

}
