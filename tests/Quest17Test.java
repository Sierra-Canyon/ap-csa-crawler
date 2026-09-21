/** Quest 17: Draw the Map, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest17Test {
    public static void run(Check c) {
        c.begin("Quest 17: Draw the Map");
        if (Harness.isStub("Quest17DrawTheMap")) { c.skip("all checks", 4, "not started"); } else {
            String[] labels = { "Size:", "Drawn:*" };
            int[] points = { 1, 1, 1, 1 };
            String[][] names = {
                { "the small map", "the small map" },
                { "a hero by the wall", "a hero by the wall" },
                { "one long row", "one long row" },
                { "level 3 of the game", "level 3 of the game" },
            };
            String[][] inputs = {
                { "6", "#######@.$M##.$.M#######" },
                { "5", "######..M##$.@######" },
                { "8", "#########M$M$M@#########" },
                { "20", "#####################@.....#$..M.......##.####.#.#########.##.#$N#.#.#..*....#.##.#..M.#.#.#####.#.##.####.#.#.#+B.#.#.##......#.#.##M.#.#.##.######.#...$.#.#.##.#$..M..#######.#.##...#............M.#####################" },
            };
            String[][] expected = {
                { "4 by 6", "# # # # # # | # @ . $ M # | # . $ . M # | # # # # # #" },
                { "4 by 5", "# # # # # | # . . M # | # $ . @ # | # # # # #" },
                { "3 by 8", "# # # # # # # # | # M $ M $ M @ # | # # # # # # # #" },
                { "11 by 20", "# # # # # # # # # # # # # # # # # # # # | # @ . . . . . # $ . . M . . . . . . . # | # . # # # # . # . # # # # # # # # # . # | # . # $ N # . # . # . . * . . . . # . # | # . # . . M . # . # . # # # # # . # . # | # . # # # # . # . # . # + B . # . # . # | # . . . . . . # . # . # # M . # . # . # | # . # # # # # # . # . . . $ . # . # . # | # . # $ . . M . . # # # # # # # . # . # | # . . . # . . . . . . . . . . . . M . # | # # # # # # # # # # # # # # # # # # # #" },
            };
            Cases.run(c, "Quest17DrawTheMap", labels, points, names, inputs, expected);
        }
        c.begin("Quest 17 challenge: Fog");
        if (Harness.isStub("Quest17Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Drawn:*" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "the small map", "the small map" },
                { "a hero by the wall", "a hero by the wall" },
                { "one long row", "one long row" },
                { "level 3 of the game", "level 3 of the game" },
            };
            String[][] inputs = {
                { "6", "#######@.$M##.$.M#######" },
                { "5", "######..M##$.@######" },
                { "8", "#########M$M$M@#########" },
                { "20", "#####################@.....#$..M.......##.####.#.#########.##.#$N#.#.#..*....#.##.#..M.#.#.#####.#.##.####.#.#.#+B.#.#.##......#.#.##M.#.#.##.######.#...$.#.#.##.#$..M..#######.#.##...#............M.#####################" },
            };
            String[][] expected = {
                { "###??? | #@.$?? | #.$??? | ?#????" },
                { "???#? | ??.M# | ?$.@# | ??###" },
                { "?????### | ????$M@# | ?????###" },
                { "###????????????????? | #@..???????????????? | #.#????????????????? | ?.?????????????????? | ???????????????????? | ???????????????????? | ???????????????????? | ???????????????????? | ???????????????????? | ???????????????????? | ????????????????????" },
            };
            Cases.run(c, "Quest17Challenge", labels, points, names, inputs, expected);
        }
    }
}
