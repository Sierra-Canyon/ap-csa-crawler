/** Quest 9: The Sealed Door, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest09Test {
    public static void run(Check c) {
        c.begin("Quest 9: The Sealed Door");
        if (Harness.isStub("Quest09TheSealedDoor")) { c.skip("all checks", 6, "not started"); } else {
            String[] labels = { "Sealed:", "Sealed again:", "Sage:" };
            int[] points = { 1, 1, 1, 1, 1 };
            String[][] names = {
                { "boss at 18 health, quest not started", "boss at 18 health, quest not started" },
                { "boss at 0 health, started, 3 gold", "boss at 0 health, started, 3 gold" },
                { "no boss, started, 2 gold", "no boss, started, 2 gold" },
                { "boss at 1 health, quest done", "boss at 1 health, quest done" },
                { "no boss, not started, 9 gold", "no boss, not started, 9 gold" },
            };
            String[][] inputs = {
                { "18", "0", "0" },
                { "0", "1", "3" },
                { "-1", "1", "2" },
                { "1", "3", "5" },
                { "-1", "0", "9" },
            };
            String[][] expected = {
                { "true", "true", "Bring me 3 gold." },
                { "false", "false", "You found it. Take this shield." },
                { "false", "false", "Still looking? You have 2 of 3." },
                { "true", "true", "Thank you again." },
                { "false", "false", "Bring me 3 gold." },
            };
            Cases.run(c, "Quest09TheSealedDoor", labels, points, names, inputs, expected);
            c.isTrue("Sealed again is written with !( ... )", 1, Cases.uses("Quest09TheSealedDoor", "!("), "there is no !( in your file");
        }
        c.begin("Quest 9 challenge: Stuck");
        if (Harness.isStub("Quest09Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Leave:", "Stuck:" };
            int[] points = { 0, 0, 0, 0, 0 };
            String[][] names = {
                { "no boss, no monsters, no keys", "no boss, no monsters, no keys" },
                { "boss beaten, 3 monsters, 1 key", "boss beaten, 3 monsters, 1 key" },
                { "boss alive, no monsters, 1 key", "boss alive, no monsters, 1 key" },
                { "no boss, 2 monsters, no keys", "no boss, 2 monsters, no keys" },
                { "boss beaten, no monsters, no keys", "boss beaten, no monsters, no keys" },
            };
            String[][] inputs = {
                { "-1", "0", "0" },
                { "0", "3", "1" },
                { "5", "0", "1" },
                { "-1", "2", "0" },
                { "0", "0", "0" },
            };
            String[][] expected = {
                { "true", "false" },
                { "true", "false" },
                { "false", "true" },
                { "false", "true" },
                { "true", "false" },
            };
            Cases.run(c, "Quest09Challenge", labels, points, names, inputs, expected);
            int bangs = Cases.count("Quest09Challenge", "!") - Cases.count("Quest09Challenge", "!=");
            c.isTrue("no ! in the file", 0, bangs == 0 && !Cases.uses("Quest09Challenge", "==false"), "there are " + bangs + " not counting !=, or the file says == false");
        }
    }
}
