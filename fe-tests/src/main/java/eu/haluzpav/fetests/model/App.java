package eu.haluzpav.fetests.model;

import eu.haluzpav.fetests.Driven;
import eu.haluzpav.fetests.MyDriver;

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
