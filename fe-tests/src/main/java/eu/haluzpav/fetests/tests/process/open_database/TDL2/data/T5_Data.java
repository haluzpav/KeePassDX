package eu.haluzpav.fetests.tests.process.open_database.TDL2.data;

import java.util.Arrays;
import java.util.Collection;

public class T5_Data extends BaseData {

    @Override
    public Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {emptyDbRoot, emptyDbFilename,
                        invalidCharsDbRoot.poll(), invalidCharsDbFilename.poll(),
                        validDbRoot, nonexistentDbFilename.poll(),
                        wrongPasswords.poll(), invalidCharsPasswords.poll(),
                        invalidCharsPasswords.poll(), wrongPasswords.poll(),
                        wrongPasswords.poll()},
                {invalidCharsDbRoot.poll(), nonexistentDbFilename.poll(),
                        nonapprovedDbRoot.poll(), emptyDbFilename,
                        validDbRoot, nonexistentDbFilename.poll(),
                        emptyPassword, wrongPasswords.poll(),
                        wrongPasswords.poll(), emptyPassword,
                        emptyPassword},
        });
    }

}
