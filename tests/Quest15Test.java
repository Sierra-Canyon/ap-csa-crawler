/** Quest 15: Read the Map, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest15Test {
    public static void run(Check c) {
        c.begin("Quest 15: Read the Map");
        if (Harness.isStub("Quest15ReadTheMap")) { c.skip("all checks", 4, "not started"); } else {
            String[] labels = { "Gold:", "Monsters:", "Hero row:", "Hero col:" };
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
                { "2", "2", "1", "1" },
                { "1", "1", "2", "3" },
                { "2", "3", "1", "6" },
                { "4", "5", "1", "1" },
            };
            Cases.run(c, "Quest15ReadTheMap", labels, points, names, inputs, expected);
        }
        c.begin("Quest 15 challenge: Nearest in the Row");
        if (Harness.isStub("Quest15Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Nearest:" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "the small map", "the small map" },
                { "a hero by the wall", "a hero by the wall" },
                { "one long row", "one long row" },
                { "a monster on each side", "a monster on each side" },
            };
            String[][] inputs = {
                { "6", "#######@.$M##.$.M#######" },
                { "5", "######..M##$.@######" },
                { "8", "#########M$M$M@#########" },
                { "8", "#########M.@..M#########" },
            };
            String[][] expected = {
                { "3" },
                { "none" },
                { "1" },
                { "2" },
            };
            Cases.run(c, "Quest15Challenge", labels, points, names, inputs, expected);
        }
    }
}
