package eu.haluzpav.fetests.tests.process.open_database.TDL2.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public abstract class BaseData {

    public static final String databaseRoot = "/storage/emulated/0/keepass/";
    public static final String defaultDatabasePath = databaseRoot + "keepass.kdbx";
    public static final String validPassword = "a";

    final Queue<String> invalidPasswords = new LinkedList<>(Arrays.asList(
            "",
            "zEman!!%&*$!&",
            "beruška",
            "Trautenberg666",
            "4 8 15 16 23 42",
            "bananas?",
            "normally worng"
    ));

    public abstract Collection<Object[]> data();

}
