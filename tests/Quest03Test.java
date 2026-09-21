/** Quest 3: Two Names One Monster, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest03Test {
    public static void run(Check c) {
        c.begin("Quest 3: Two Names One Monster");
        if (Harness.isStub("Quest03TwoNamesOneMonster")) { c.skip("all checks", 3, "not started"); } else {
            String[] labels = { "First:", "Alias:", "Copy:", "First after:", "Alias after:", "Copy after:" };
            int[] points = { 1, 1, 1 };
            String[][] names = {
                { "Grub, 9 health, hits of 3 and 4", "Grub, 9 health, hits of 3 and 4" },
                { "Ogre, 5 health, hits of 7 and 1", "Ogre, 5 health, hits of 7 and 1" },
                { "Bat, 4 health, hits of 0 and 4", "Bat, 4 health, hits of 0 and 4" },
            };
            String[][] inputs = {
                { "Grub", "9", "3", "4" },
                { "Ogre", "5", "7", "1" },
                { "Bat", "4", "0", "4" },
            };
            String[][] expected = {
                { "6", "6", "9", "6", "5", "5" },
                { "0", "0", "5", "0", "4", "4" },
                { "4", "4", "4", "4", "0", "0" },
            };
            Cases.run(c, "Quest03TwoNamesOneMonster", labels, points, names, inputs, expected);
        }
        c.begin("Quest 3 challenge: Change the Leader");
        if (Harness.isStub("Quest03Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Leader:", "Second:" };
            int[] points = { 0, 0 };
            String[][] names = {
                { "Rook leads, then Mote", "Rook leads, then Mote" },
                { "Vex leads, then Ash", "Vex leads, then Ash" },
            };
            String[][] inputs = {
                { "Rook", "10", "4", "Mote", "7", "3" },
                { "Vex", "2", "5", "Ash", "3", "1" },
            };
            String[][] expected = {
                { "Mote (4/7 health, attack 3)", "Rook (10/10 health, attack 4)" },
                { "Ash (0/3 health, attack 1)", "Vex (2/2 health, attack 5)" },
            };
            Cases.run(c, "Quest03Challenge", labels, points, names, inputs, expected);
            c.isTrue("exactly two heroes are constructed", 0, Cases.count("Quest03Challenge", "new Hero(") == 2, "new Hero( appears " + Cases.count("Quest03Challenge", "new Hero(") + " times");
        }
    }
}
