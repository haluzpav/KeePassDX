package eu.haluzpav.fetests.tests.process.open_database.TDL2.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class BaseData {

    public static final String validPassword = "a";
    static final String validDbRoot = "/storage/emulated/0/keepass/";
    static final String emptyDbRoot = "";
    static final String validDbFilename = "keepass.kdbx";
    static final String emptyDbFilename = "";
    static final String defaultDatabasePath = validDbRoot + validDbFilename;
    final static String emptyPassword = "";
    final Queue<String> invalidCharsDbRoot = new LinkedList<>(Arrays.asList(
            "/storage/emulated/0/keepass/ɐıʃɐɹʇsnɐ/",
            "/storage/emulated/0/keepass/\uD83C\uDDEA\uD83C\uDDFA/"
    ));
    final Queue<String> nonapprovedDbRoot = new LinkedList<>(Arrays.asList(
            "/",
            "/user/",
            "/storage/emulated/0/someotherapp/"
    ));
    final Queue<String> invalidCharsDbFilename = new LinkedList<>(Arrays.asList(
            "/storage/emulated/0/keepass/ɐıʃɐɹʇsnɐ/",
            "/storage/emulated/0/keepass/\uD83C\uDDEA\uD83C\uDDFA/"
    ));
    final Queue<String> nonexistentDbFilename = new LinkedList<>( // my peak java knowledge
            new Random().ints()
                    .limit(3)
                    .mapToObj(String::valueOf)
                    .map(s -> s + ".kdbx")
                    .collect(Collectors.toList())
    );
    final Queue<String> wrongPasswords = new LinkedList<>(Arrays.asList(
            emptyPassword,
            "zEman!!%&*$!&",
            "beruška",
            "Trautenberg666",
            "4 8 15 16 23 42",
            "bananas?",
            "normally worng"
    ));
    final Queue<String> invalidCharsPasswords = new LinkedList<>(Arrays.asList(
            "sʞɔns ɐıʃɐɹʇsnɐ",
            "\uD83C\uDF4C \uD83C\uDFBE \uD83C\uDDEA\uD83C\uDDFA" // emojis
    ));

    public abstract Collection<Object[]> data();

}
