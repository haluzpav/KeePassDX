package eu.haluzpav.fetests.tests.inputs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import eu.haluzpav.fetests.model.dialogs.PassGeneratorDialog;
import eu.haluzpav.fetests.tests.inputs.data.PassGenData;
import eu.haluzpav.fetests.tests.process.open_database.TDL2.data.BaseProcessData;

@RunWith(Parameterized.class)
public class PassGenTest extends BaseInputsTest {

    // TODO test insertion of generated pass to edit screen

    private static int MIN_LENGTH_FOR_CHARS = 40;

    @Parameterized.Parameter(0)
    public int length;

    @Parameterized.Parameter(1)
    public boolean[] switches;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return new PassGenData().data();
    }

    @BeforeClass
    public static void setUp() {
        BaseInputsTest.setUp();

        tryGoToOpenDbScreenTest();
        enterCorrectPathTest(BaseProcessData.defaultDatabasePath);
        enterCorrectPassTest(BaseProcessData.validPassword);

        openAddEntryTest();
        openPassGenTest();
    }

    @Test
    public void generatedPassTest() {
        passGeneratorDialog.setLength(length);
        passGeneratorDialog.setSwitchStates(switches);

        String pass = passGeneratorDialog.generatePass();

        if (length > 0 && areSwitchesValid()) {
            Assert.assertEquals(length, pass.length());
            if (length >= MIN_LENGTH_FOR_CHARS)
                assertContainsFromEach(pass); // don't test if too low to be reliable
            else assertContainsAtLeastOne(pass);
        } else if (!pass.equals("") && !pass.equals(PassGeneratorDialog.PASS_HINT)) {
            // TODO catch toast
            Assert.fail();
        }
    }

    private void assertContainsFromEach(String pass) {
        // probabilities:
        // contains one category 1-(7/8)^L
        // contains two categories (1-(7/8)^L)^2
        // does not contain at least one category 1-(1-(7/8)^L)^8 = 0.00155 for L=64
        // good enough

        for (int i = 0; i < PassGenData.switchChars.length; i++) {
            if (!switches[i]) continue;
            String chars = PassGenData.switchChars[i];
            boolean has = false;
            for (int c = 0; c < chars.length(); c++) {
                String charac = chars.substring(c, c + 1);
                if (!pass.contains(charac)) continue;
                has = true;
                break;
            }
            if (!has) Assert.fail(pass + " " + Arrays.toString(switches) + " " + chars);
        }
    }

    private void assertContainsAtLeastOne(String pass) {
        for (int i = 0; i < pass.length(); i++) {
            String p = pass.substring(i, i + 1);
            for (String switchChars : PassGenData.switchChars) {
                if (switchChars.contains(p)) return;
            }
        }
        Assert.fail();
    }

    private boolean areSwitchesValid() {
        for (boolean s : switches) {
            if (s) return true;
        }
        return false;
    }
}
