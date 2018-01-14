package com.haluzpav.fetests.model;

import com.haluzpav.fetests.Driven;
import com.haluzpav.fetests.MyDriver;

import org.openqa.selenium.support.PageFactory;

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
