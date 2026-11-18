/** Project build guide, Step 6: gold. Built by teacher/tools/make_project.py. */
public class DungeonStep6Test {
    public static void run(Check c) {
        c.begin("Project, Step 6: gold");
        if (Harness.isStub("DungeonInOneMain")) { c.skip("all checks", 10, "not started"); return; }
        String[] labels0 = { "Result:", "Gold:", "Health left:", "Steps:", "Monsters left:", "Hero at:", "Final map:*" };
        int[] points0 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        String[][] names0 = {
            { "picking up gold" },
            { "a command the game does not know" },
            { "the door is sealed while a monster lives" },
            { "walking and walls" },
            { "down, down, right" },
            { "commands that change nothing yet" },
            { "a long run of commands" },
            { "the report before any command" },
            { "a hero somewhere else" },
            { "a wider map" },
        };
        String[][] inputs0 = {
            { "6", "#######@.$M##.$.+#######", "10", "d", "d", "s", "a", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "x", "d", "jump", "d", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "s", "d", "d", "d", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "a", "w", "s", "s", "w", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "s", "s", "d", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "w", "a", "x", "jump", "w", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "w", "a", "w", "a", "w", "a", "w", "a", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "quit" },
            { "6", "#######.M$M##.@.+#######", "10", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "quit" },
        };
        String[][] expected0 = {
            { "quit", "2", "10", "4", "1", "2,2", "###### | #...M# | #.@.+# | ######" },
            { "quit", "1", "10", "2", "1", "1,3", "###### | #..@M# | #.$.+# | ######" },
            { "quit", "1", "10", "3", "1", "2,3", "###### | #..$M# | #..@+# | ######" },
            { "quit", "0", "10", "2", "1", "1,1", "###### | #@.$M# | #.$.+# | ######" },
            { "quit", "0", "10", "3", "2", "3,2", "########## | #..$.#..M# | #.##.#.#.# | #.@M...#+# | ##########" },
            { "quit", "0", "10", "0", "1", "1,1", "###### | #@.$M# | #.$.+# | ######" },
            { "quit", "0", "10", "0", "2", "1,1", "########## | #@.$.#..M# | #.##.#.#.# | #..M...#+# | ##########" },
            { "quit", "0", "10", "0", "1", "1,1", "###### | #@.$M# | #.$.+# | ######" },
            { "quit", "0", "10", "0", "2", "2,2", "###### | #.M$M# | #.@.+# | ######" },
            { "quit", "0", "10", "0", "2", "1,1", "########## | #@.$.#..M# | #.##.#.#.# | #..M...#+# | ##########" },
        };
        Cases.run(c, "DungeonInOneMain", labels0, points0, names0, inputs0, expected0);
    }
}
