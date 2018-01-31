package eu.haluzpav.fetests.tests.process.open_database.TDL2.data;

import java.util.Arrays;
import java.util.Collection;

public class T4_Data extends BaseProcessData {

    @Override
    public Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {validDbRoot, nonexistentDbFilename, wrongPasswords.poll()} // new db with "random" valid pass
        });
    }

}
