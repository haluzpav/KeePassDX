package eu.haluzpav.fetests.tests.process.open_database.TDL2.data;

import java.util.Arrays;
import java.util.Collection;

public class T1_Data extends BaseData {

    @Override
    public Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {wrongPasswords.poll(), wrongPasswords.poll(), validPassword},
                {wrongPasswords.poll(), wrongPasswords.poll(), validPassword},
                {wrongPasswords.poll(), wrongPasswords.poll(), validPassword},
        });
    }

}
