package com.haluzpav.fetests.model;

import com.haluzpav.fetests.Driven;
import com.haluzpav.fetests.MyDriver;

public class App implements Driven {

    private MyDriver driver;

    public App(MyDriver driver) {
        this.driver = driver;
    }

    public OpenPage openPage() {
        return new OpenPage(driver);
    }

    @Override
    public final MyDriver driver() {
        return driver;
    }
}
