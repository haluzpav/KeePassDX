package eu.haluzpav.fetests.model;

import org.openqa.selenium.support.PageFactory;

import eu.haluzpav.fetests.Driven;
import eu.haluzpav.fetests.MyDriver;

abstract class BasePage implements Driven {

    private MyDriver driver;

    BasePage(MyDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public final MyDriver driver() {
        return driver;
    }

}
