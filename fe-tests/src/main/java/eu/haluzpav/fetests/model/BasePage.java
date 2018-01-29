package eu.haluzpav.fetests.model;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

import eu.haluzpav.fetests.Driven;
import eu.haluzpav.fetests.MyDriver;
import eu.haluzpav.fetests.tests.BaseTest;

public abstract class BasePage implements Driven {

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
        field.click();
        field.clear();

        // does not work on "hidden" password fields
        field.sendKeys(text);
    }

    protected void fillPasswordField(WebElement field, String text) {
        field.click();
        field.clear();

        // horrible hack but works
        sendAdbText(text);
    }

    private void sendAdbText(String text) {
        // does not work with some special characters
        try {
            Runtime.getRuntime().exec(BaseTest.adb() + " shell input text " + text).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * List of elements unique for the screen. No other screen should have all these elements.
     * Used to determine which screen is opened.
     */
    protected List<WebElement> uniqueElements() {
        throw new IllegalStateException("not implemented");
    }

    public final boolean isOpened() {
        try {
            for (WebElement element : uniqueElements()) {
                if (!element.isDisplayed()) return false;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
