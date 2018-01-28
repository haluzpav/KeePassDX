package eu.haluzpav.fetests.model.screens;

import eu.haluzpav.fetests.model.BasePage;
import eu.haluzpav.fetests.model.toolbar.Toolbar;

abstract class BaseScreen extends BasePage {

    private Toolbar toolbar;

    BaseScreen() {
        super();
        toolbar = createToolbar();
    }

    protected Toolbar createToolbar() {
        return new Toolbar();
    }

    public Toolbar toolbar() {
        return toolbar;
    }

}
