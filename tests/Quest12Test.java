/** Quest 12: Health Bar, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest12Test {
    public static void run(Check c) {
        c.begin("Quest 12: Health Bar");
        if (Harness.isStub("Quest12HealthBar")) { c.skip("all checks", 5, "not started"); } else {
            String[] labels = { "Bar:", "Percent:" };
            int[] points = { 1, 1, 1, 1, 1 };
            String[][] names = {
                { "4 of 10", "4 of 10" },
                { "0 of 10", "0 of 10" },
                { "10 of 10", "10 of 10" },
                { "1 of 3", "1 of 3" },
                { "7 of 8", "7 of 8" },
            };
            String[][] inputs = {
                { "4", "10" },
                { "0", "10" },
                { "10", "10" },
                { "1", "3" },
                { "7", "8" },
            };
            String[][] expected = {
                { "[####......]", "40" },
                { "[..........]", "0" },
                { "[##########]", "100" },
                { "[#..]", "33" },
                { "[#######.]", "87" },
            };
            Cases.run(c, "Quest12HealthBar", labels, points, names, inputs, expected);
        }
        c.begin("Quest 12 challenge: Boss Bar");
        if (Harness.isStub("Quest12Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Boss:" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "3 of 6", "3 of 6" },
                { "18 of 18", "18 of 18" },
                { "0 of 3", "0 of 3" },
                { "7 of 9", "7 of 9" },
            };
            String[][] inputs = {
                { "3", "6" },
                { "18", "18" },
                { "0", "3" },
                { "7", "9" },
            };
            String[][] expected = {
                { "[##|#.|..]" },
                { "[######|######|######]" },
                { "[.|.|.]" },
                { "[###|###|#..]" },
            };
            Cases.run(c, "Quest12Challenge", labels, points, names, inputs, expected);
        }
    }
}
