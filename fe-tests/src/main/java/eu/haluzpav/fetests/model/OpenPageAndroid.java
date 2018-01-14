package eu.haluzpav.fetests.model;

import eu.haluzpav.fetests.MyDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

final class OpenPageAndroid extends BasePage {

    // THIS CRASHES
    @AndroidFindBy(id = "menu_donate")
    private AndroidElement donateButton;
    // supposedly needs automationName=UiAutomator2 but does not help

//    @AndroidFindBy(id = "menu_donate")
//    private AndroidElement donateButton;
    // needs automationName=Selendroid but it needs some permissions and i am lazy

    // https://github.com/appium/java-client/blob/master/docs/Page-objects.md

    OpenPageAndroid(MyDriver driver) {
        super(driver);
    }

    public void clickDonate() {
        donateButton.click();

        // this works thought ¯\_(ツ)_/¯
//        AndroidElement e = driver().findElement(By.id("menu_donate"));
//        e.click();
    }

}
