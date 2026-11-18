/** Project build guide, Step 1: read the input. Built by teacher/tools/make_project.py. */
public class DungeonStep1Test {
    public static void run(Check c) {
        c.begin("Project, Step 1: read the input");
        if (Harness.isStub("DungeonInOneMain")) { c.skip("all checks", 1, "not started"); return; }
        String[] labels0 = {  };
        int[] points0 = { 1 };
        String[][] names0 = {
            { "reads the width, the map and the health" },
        };
        String[][] inputs0 = {
            { "6", "#######@.$M##.$.+#######", "10", "quit" },
        };
        String[][] expected0 = {
            {  },
        };
        Cases.run(c, "DungeonInOneMain", labels0, points0, names0, inputs0, expected0);
    }
}
