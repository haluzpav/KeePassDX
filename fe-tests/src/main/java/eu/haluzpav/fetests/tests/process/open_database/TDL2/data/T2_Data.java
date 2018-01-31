package eu.haluzpav.fetests.tests.process.open_database.TDL2.data;

import java.util.Arrays;
import java.util.Collection;

public class T2_Data extends BaseProcessData {

    @Override
    public Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {emptyDbRoot + emptyDbFilename, invalidCharsDbRoot.poll() + invalidCharsDbFilename.poll(), defaultDatabasePath, wrongPasswords.poll(), validPassword},
                {invalidCharsDbRoot.poll() + emptyDbFilename, nonapprovedDbRoot.poll() + nonexistentDbFilename.poll(), defaultDatabasePath, invalidCharsPasswords.poll(), validPassword},
                {nonapprovedDbRoot.poll() + validDbFilename, emptyDbRoot + emptyDbFilename, defaultDatabasePath, wrongPasswords.poll(), validPassword},
        });
    }

}
