/** Quest 16: Line of Fire, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest16Test {
    public static void run(Check c) {
        c.begin("Quest 16: Line of Fire");
        if (Harness.isStub("Quest16LineOfFire")) { c.skip("all checks", 9, "not started"); } else {
            String[] labels = { "Shot:", "Stopped at:" };
            int[] points = { 1, 1, 1, 1, 1, 1, 1, 1 };
            String[][] names = {
                { "a monster three squares away", "a monster three squares away" },
                { "the same monster, reach of 2", "the same monster, reach of 2" },
                { "a wall in the way", "a wall in the way" },
                { "a person in the way", "a person in the way" },
                { "a door", "a door" },
                { "two monsters in line", "two monsters in line" },
                { "a boss exactly at full reach", "a boss exactly at full reach" },
                { "nothing within reach", "nothing within reach" },
            };
            String[][] inputs = {
                { "#@..M..#", "1", "6" },
                { "#@..M..#", "1", "2" },
                { "#@.#M..#", "1", "6" },
                { "#@.NM#", "1", "6" },
                { "#@...+", "1", "6" },
                { "#@M.M#", "1", "3" },
                { "#.@..B.#", "2", "3" },
                { "#@.....#", "1", "3" },
            };
            String[][] expected = {
                { "hits", "4" },
                { "falls short", "-1" },
                { "blocked", "3" },
                { "blocked", "3" },
                { "blocked", "5" },
                { "hits", "2" },
                { "hits", "5" },
                { "falls short", "-1" },
            };
            Cases.run(c, "Quest16LineOfFire", labels, points, names, inputs, expected);
            c.isTrue("no break", 1, !Cases.uses("Quest16LineOfFire", "break;"), "the file uses break");
        }
        c.begin("Quest 16 challenge: Either Way");
        if (Harness.isStub("Quest16Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Shot:", "Stopped at:" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "left, a monster", "left, a monster" },
                { "left, a wall first", "left, a wall first" },
                { "right, a monster", "right, a monster" },
                { "left, out of reach", "left, out of reach" },
            };
            String[][] inputs = {
                { "#M..@..#", "4", "a", "6" },
                { "#M.#@..#", "4", "a", "6" },
                { "#..M@.M#", "4", "d", "6" },
                { "#M...@.#", "5", "a", "3" },
            };
            String[][] expected = {
                { "hits", "1" },
                { "blocked", "3" },
                { "hits", "6" },
                { "falls short", "-1" },
            };
            Cases.run(c, "Quest16Challenge", labels, points, names, inputs, expected);
            int loops = Cases.count("Quest16Challenge", "while(") + Cases.count("Quest16Challenge", "for(");
            c.isTrue("one loop does both directions", 0, loops == 1, "there are " + loops + " loops in the file");
        }
    }
}
