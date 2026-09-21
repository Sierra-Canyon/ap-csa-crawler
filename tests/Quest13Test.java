/** Quest 13: Battle Report, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest13Test {
    public static void run(Check c) {
        c.begin("Quest 13: Battle Report");
        if (Harness.isStub("Quest13BattleReport")) { c.skip("all checks", 4, "not started"); } else {
            String[] labels = { "Hits:", "Total:", "Biggest:", "Average:~" };
            int[] points = { 1, 1, 1, 1 };
            String[][] names = {
                { "five swings, two misses", "five swings, two misses" },
                { "one swing, a miss", "one swing, a miss" },
                { "four swings, no misses", "four swings, no misses" },
                { "the biggest is in the middle", "the biggest is in the middle" },
            };
            String[][] inputs = {
                { "5", "3", "0", "4", "6", "0" },
                { "1", "0" },
                { "4", "2", "2", "2", "3" },
                { "3", "1", "9", "1" },
            };
            String[][] expected = {
                { "3", "13", "6", "2.6" },
                { "0", "0", "0", "0.0" },
                { "4", "9", "3", "2.25" },
                { "3", "11", "9", "3.67" },
            };
            Cases.run(c, "Quest13BattleReport", labels, points, names, inputs, expected);
        }
        c.begin("Quest 13 challenge: The Weakest Hit");
        if (Harness.isStub("Quest13Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Misses:", "Smallest hit:" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "five swings, two misses", "five swings, two misses" },
                { "one swing, a miss", "one swing, a miss" },
                { "four swings, no misses", "four swings, no misses" },
                { "misses first", "misses first" },
            };
            String[][] inputs = {
                { "5", "3", "0", "4", "6", "0" },
                { "1", "0" },
                { "4", "2", "2", "2", "3" },
                { "3", "0", "0", "7" },
            };
            String[][] expected = {
                { "2", "3" },
                { "1", "none" },
                { "0", "2" },
                { "2", "7" },
            };
            Cases.run(c, "Quest13Challenge", labels, points, names, inputs, expected);
        }
    }
}
