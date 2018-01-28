package eu.haluzpav.fetests.tests.process.open_database;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.haluzpav.fetests.tests.BaseTest;

public class BaseDbProcessTest extends BaseTest {

    protected final String validPassword = "a";
    protected final List<String> invalidPasswords = Collections.unmodifiableList(Arrays.asList(
            "Trautenberg666", "4 8 15 16 23 42", "bananas?"));

}
