/** Project build guide, Step 4: where the hero is trying to go. Built by teacher/tools/make_project.py. */
public class DungeonStep4Test {
    public static void run(Check c) {
        c.begin("Project, Step 4: where the hero is trying to go");
        if (Harness.isStub("DungeonInOneMain")) { c.skip("all checks", 5, "not started"); return; }
        String[] labels0 = { "Target:*" };
        int[] points0 = { 1, 1, 1, 1, 1 };
        String[][] names0 = {
            { "w and a, into walls" },
            { "s, w and a, into walls" },
            { "s, onto floor" },
            { "d, onto floor" },
            { "words that are not moves" },
        };
        String[][] inputs0 = {
            { "6", "#######@.$M##.$.+#######", "10", "w", "a", "quit" },
            { "7", "########@M.M+########", "10", "s", "w", "a", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "s", "quit" },
            { "10", "###########@.$.#..M##.##.#.#.##..M...#+###########", "10", "d", "quit" },
            { "6", "#######@.$M##.$.+#######", "10", "x", "jump", "quit" },
        };
        String[][] expected0 = {
            { "1 # | 6 #" },
            { "15 # | 1 # | 7 #" },
            { "21 ." },
            { "12 ." },
            { "" },
        };
        Cases.run(c, "DungeonInOneMain", labels0, points0, names0, inputs0, expected0);
    }
}
