/** Project build guide, Step 3: the command loop. Built by teacher/tools/make_project.py. */
public class DungeonStep3Test {
    public static void run(Check c) {
        c.begin("Project, Step 3: the command loop");
        if (Harness.isStub("DungeonInOneMain")) { c.skip("all checks", 5, "not started"); return; }
        String[] labels0 = { "Result:", "Gold:", "Health left:", "Steps:", "Monsters left:", "Hero at:", "Final map:*" };
        int[] points0 = { 1, 1, 1, 1, 1 };
        String[][] names0 = {
            { "commands that change nothing yet" },
            { "a long run of commands" },
            { "the report before any command" },
            { "a hero somewhere else" },
            { "a wider map" },
        };
        String[][] inputs0 = {
            { "6", "#######@.$M##.$.+#######", "10", "w", "a", "x", "jump", "w", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "w", "a", "w", "a", "w", "a", "w", "a", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "quit" },
            { "6", "#######.M$M##.@.+#######", "10", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "quit" },
        };
        String[][] expected0 = {
            { "quit", "0", "10", "0", "1", "1,1", "###### | #@.$M# | #.$.+# | ######" },
            { "quit", "0", "10", "0", "2", "1,1", "########## | #@.$.#..M# | #.##.#.#.# | #..M...#+# | ##########" },
            { "quit", "0", "10", "0", "1", "1,1", "###### | #@.$M# | #.$.+# | ######" },
            { "quit", "0", "10", "0", "2", "2,2", "###### | #.M$M# | #.@.+# | ######" },
            { "quit", "0", "10", "0", "2", "1,1", "########## | #@.$.#..M# | #.##.#.#.# | #..M...#+# | ##########" },
        };
        Cases.run(c, "DungeonInOneMain", labels0, points0, names0, inputs0, expected0);
    }
}
