/** Quest 5: Parse the Command, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest05Test {
    public static void run(Check c) {
        c.begin("Quest 5: Parse the Command");
        if (Harness.isStub("Quest05ParseTheCommand")) { c.skip("all checks", 4, "not started"); } else {
            String[] labels = { "Verb:", "Rest:", "Space at:", "Letters:" };
            int[] points = { 1, 1, 1, 1 };
            String[][] names = {
                { "fire d", "fire d" },
                { "cast spark d", "cast spark d" },
                { "lead Mote", "lead Mote" },
                { "throw w", "throw w" },
            };
            String[][] inputs = {
                { "fire d" },
                { "cast spark d" },
                { "lead Mote" },
                { "throw w" },
            };
            String[][] expected = {
                { "[fire]", "[d]", "4", "6" },
                { "[cast]", "[spark d]", "4", "12" },
                { "[lead]", "[Mote]", "4", "9" },
                { "[throw]", "[w]", "5", "7" },
            };
            Cases.run(c, "Quest05ParseTheCommand", labels, points, names, inputs, expected);
        }
        c.begin("Quest 5 challenge: Three Words");
        if (Harness.isStub("Quest05Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Verb:", "Spell:", "Way:" };
            int[] points = { 0, 0, 0 };
            String[][] names = {
                { "cast spark d", "cast spark d" },
                { "cast quake w", "cast quake w" },
                { "cast zap a", "cast zap a" },
            };
            String[][] inputs = {
                { "cast spark d" },
                { "cast quake w" },
                { "cast zap a" },
            };
            String[][] expected = {
                { "[cast]", "[spark]", "[d]" },
                { "[cast]", "[quake]", "[w]" },
                { "[cast]", "[zap]", "[a]" },
            };
            Cases.run(c, "Quest05Challenge", labels, points, names, inputs, expected);
        }
    }
}
