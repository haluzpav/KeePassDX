package eu.haluzpav.fetests.tests.inputs.data;

import java.util.Arrays;
import java.util.Collection;

import eu.haluzpav.fetests.Data;
import eu.haluzpav.fetests.model.dialogs.PassGeneratorDialog;

public class PassGenData implements Data {

    public static final String[] switchChars = new String[]{
            "abcdefghijklmnopqrstuvwxyz".toUpperCase(),
            "abcdefghijklmnopqrstuvwxyz",
            "1234567890",
            "-",
            "_",
            " ",
            "&/,^@.#:%\\='$!*`;+",
            "[](){}<>",
    };

    private static boolean[] itb(int... a) {
        if (a.length != PassGeneratorDialog.N_SWITCHES) throw new IllegalStateException();
        boolean[] b = new boolean[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] != 0;
        }
        return b;
    }

    @Override
    public Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // mc/dc length+switches
                {-13, itb(0, 1, 0, 1, 1, 1, 0, 1)},
                {0, itb(0, 0, 0, 0, 0, 0, 0, 0)},
                {1, itb(0, 1, 1, 1, 0, 1, 1, 1)},
                {42, itb(0, 0, 0, 0, 0, 0, 0, 0)},
                // pairwise switches
                {64, itb(0, 0, 0, 0, 0, 0, 0, 0)},
                {64, itb(0, 1, 1, 1, 1, 1, 1, 1)},
                {64, itb(1, 0, 1, 0, 1, 0, 1, 0)},
                {64, itb(1, 1, 0, 1, 0, 1, 0, 1)},
                {64, itb(0, 0, 0, 1, 1, 0, 0, 1)},
                {64, itb(0, 1, 1, 0, 0, 1, 1, 0)},
                {64, itb(1, 0, 1, 1, 0, 1, 0, 0)},
                {64, itb(1, 1, 0, 0, 1, 0, 1, 1)},
        });
    }

}
