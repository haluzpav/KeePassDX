package eu.haluzpav.fetests.tests.consistency;

import org.junit.Assert;
import org.junit.Test;

import eu.haluzpav.fetests.model.toolbar.ToolbarOption;
import eu.haluzpav.fetests.tests.BaseTest;

abstract class BaseConsistencyTest extends BaseTest {

    @Test
    public void s90_goToStart() {
        // need to go back manually because Parametrized runner does not restart app
        groupScreen.toolbar().click(ToolbarOption.LOCK_DB);
        Assert.assertTrue(isOnEnterPass());
    }
}
