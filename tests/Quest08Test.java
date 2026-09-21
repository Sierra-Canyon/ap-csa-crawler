/** Quest 8: Can I Cast, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest08Test {
    public static void run(Check c) {
        c.begin("Quest 8: Can I Cast");
        if (Harness.isStub("Quest08CanICast")) { c.skip("all checks", 8, "not started"); } else {
            String[] labels = { "Can cast:", "Mana after:" };
            int[] points = { 1, 1, 1, 1, 1, 1, 1, 1 };
            String[][] names = {
                { "spark, mana 6, distance 3", "spark, mana 6, distance 3" },
                { "spark, mana 1", "spark, mana 1" },
                { "spark, distance 5", "spark, distance 5" },
                { "quake, mana 4, distance 2", "quake, mana 4, distance 2" },
                { "quake in reach, mana 3", "quake in reach, mana 3" },
                { "quake, distance 3", "quake, distance 3" },
                { "no spell at all", "no spell at all" },
                { "spark, exactly enough of both", "spark, exactly enough of both" },
            };
            String[][] inputs = {
                { "spark", "6", "3" },
                { "spark", "1", "3" },
                { "spark", "6", "5" },
                { "quake", "4", "2" },
                { "quake", "3", "1" },
                { "quake", "6", "3" },
                { "none", "6", "1" },
                { "spark", "2", "4" },
            };
            String[][] expected = {
                { "true", "4" },
                { "false", "1" },
                { "false", "6" },
                { "true", "0" },
                { "false", "3" },
                { "false", "6" },
                { "false", "6" },
                { "true", "0" },
            };
            Cases.run(c, "Quest08CanICast", labels, points, names, inputs, expected);
        }
        c.begin("Quest 8 challenge: Does the Shot Fly");
        if (Harness.isStub("Quest08Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Shot:" };
            int[] points = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            String[][] names = {
                { "# with 3 arrows", "# with 3 arrows" },
                { "N with 1 arrows", "N with 1 arrows" },
                { "+ with 2 arrows", "+ with 2 arrows" },
                { "M with 1 arrows", "M with 1 arrows" },
                { "B with 5 arrows", "B with 5 arrows" },
                { ". with 1 arrows", ". with 1 arrows" },
                { "$ with 1 arrows", "$ with 1 arrows" },
                { "M with 0 arrows", "M with 0 arrows" },
                { "# with 0 arrows", "# with 0 arrows" },
            };
            String[][] inputs = {
                { "#", "3" },
                { "N", "1" },
                { "+", "2" },
                { "M", "1" },
                { "B", "5" },
                { ".", "1" },
                { "$", "1" },
                { "M", "0" },
                { "#", "0" },
            };
            String[][] expected = {
                { "blocked" },
                { "blocked" },
                { "blocked" },
                { "hits" },
                { "hits" },
                { "flies on" },
                { "flies on" },
                { "no arrows" },
                { "no arrows" },
            };
            Cases.run(c, "Quest08Challenge", labels, points, names, inputs, expected);
        }
    }
}
