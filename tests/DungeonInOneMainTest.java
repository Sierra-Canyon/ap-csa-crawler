/** The fall project: Dungeon in One Main. Built by teacher/tools/make_project.py. */
public class DungeonInOneMainTest {
    public static void run(Check c) {
        c.begin("Project: Dungeon in One Main");
        if (Harness.isStub("DungeonInOneMain")) { c.skip("all checks", 40, "not started"); return; }
        String[] labels = { "Result:", "Gold:", "Health left:", "Steps:", "Monsters left:", "Hero at:", "Final map:*" };
        int[] points = { 5, 5, 5, 5, 5, 5, 5, 5 };
        String[][] names = {
            { "walking and walls" },
            { "picking up gold" },
            { "a fight, then walking on" },
            { "the door is sealed while a monster lives" },
            { "winning, and the commands after it" },
            { "losing, and the commands after it" },
            { "a command the game does not know" },
            { "a bigger level, start to finish" },
        };
        String[][] inputs = {
            { "6", "#######@.$M##.$.+#######", "10", "a", "w", "s", "s", "w", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "d", "d", "s", "a", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "d", "d", "d", "d", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "s", "d", "d", "d", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "d", "d", "d", "d", "s", "a", "a", "quit" },
            { "7", "########@M.M+########", "3", "d", "d", "d", "d", "d", "d", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "x", "d", "jump", "d", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "d", "d", "d", "s", "s", "a", "d", "d", "d", "w", "w", "d", "d", "d", "s", "s", "quit" },
        };
        String[][] expected = {
            { "quit", "0", "10", "2", "1", "1,1", "###### | #@.$M# | #.$.+# | ######" },
            { "quit", "2", "10", "4", "1", "2,2", "###### | #...M# | #.@.+# | ######" },
            { "quit", "1", "8", "3", "0", "1,4", "###### | #...@# | #.$.+# | ######" },
            { "quit", "1", "10", "3", "1", "2,3", "###### | #..$M# | #..@+# | ######" },
            { "won", "1", "8", "4", "0", "2,4", "###### | #....# | #.$.@# | ######" },
            { "lost", "0", "0", "2", "0", "1,3", "####### | #..@.+# | #######" },
            { "quit", "1", "10", "2", "1", "1,3", "###### | #..@M# | #.$.+# | ######" },
            { "won", "1", "6", "13", "0", "3,8", "########## | #....#...# | #.##.#.#.# | #......#@# | ##########" },
        };
        Cases.run(c, "DungeonInOneMain", labels, points, names, inputs, expected);

        c.begin("Project extra: the f command");
        String probe = "";
        try { probe = Harness.run("DungeonInOneMain", String.join("\n", new String[] { "9", "##########@..M.M###.#M$...##########", "10", "d", "f", "quit" }) + "\n"); } catch (Harness.NotRunnable e) { probe = ""; }
        if (!probe.contains("Arrows left:")) {
            c.skip("all checks", 0, "not started");
        } else {
            String[] extraLabels = { "Arrows left:", "Monsters left:", "Health left:", "Final map:*" };
            int[] none = { 0, 0, 0, 0, 0, 0 };
            String[][] extraNames = {
                { "an arrow hits a monster two squares away" },
                { "the first monster in line, not the second" },
                { "a monster exactly at full reach" },
                { "out of reach" },
                { "a wall stops it" },
                { "only three arrows" },
            };
            String[][] extraInputs = {
                { "9", "##########@..M.M###.#M$...##########", "10", "d", "f", "quit" },
                { "9", "##########@..M.M###.#M$...##########", "10", "d", "d", "f", "d", "d", "f", "quit" },
                { "9", "##########@..M.M###.#M$...##########", "10", "f", "quit" },
                { "8", "#########@...M.#########", "10", "f", "quit" },
                { "9", "##########@..M.M###.#M$...##########", "10", "s", "f", "quit" },
                { "9", "##########@..M.M###.#M$...##########", "10", "f", "f", "f", "d", "f", "quit" },
            };
            String[][] extraExpected = {
                { "2", "2", "10", "######### | #.@...M## | #.#M$...# | #########" },
                { "1", "1", "10", "######### | #....@.## | #.#M$...# | #########" },
                { "2", "2", "10", "######### | #@....M## | #.#M$...# | #########" },
                { "2", "1", "10", "######## | #@...M.# | ########" },
                { "2", "3", "10", "######### | #...M.M## | #@#M$...# | #########" },
                { "0", "2", "10", "######### | #.@...M## | #.#M$...# | #########" },
            };
            Cases.run(c, "DungeonInOneMain", extraLabels, none, extraNames, extraInputs, extraExpected);
        }
    }
}
