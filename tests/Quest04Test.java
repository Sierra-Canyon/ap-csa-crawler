/** Quest 4: One Round, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest04Test {
    public static void run(Check c) {
        c.begin("Quest 4: One Round");
        if (Harness.isStub("Quest04OneRound")) { c.skip("all checks", 3, "not started"); } else {
            String[] labels = { "Monster now:", "Hero now:", "Gained:", "Monster alive:" };
            int[] points = { 1, 1, 1 };
            String[][] names = {
                { "Rook against a Grub, potion of 5", "Rook against a Grub, potion of 5" },
                { "Mote against an Ogre, potion of 9", "Mote against an Ogre, potion of 9" },
                { "Brannoch against a Bat, potion of 4", "Brannoch against a Bat, potion of 4" },
            };
            String[][] inputs = {
                { "Rook", "10", "4", "Grub", "9", "3", "5" },
                { "Mote", "7", "3", "Ogre", "3", "6", "9" },
                { "Brannoch", "10", "5", "Bat", "20", "1", "4" },
            };
            String[][] expected = {
                { "5", "10", "2", "true" },
                { "0", "7", "5", "false" },
                { "15", "10", "1", "true" },
            };
            Cases.run(c, "Quest04OneRound", labels, points, names, inputs, expected);
        }
        c.begin("Quest 4 challenge: Cast It");
        if (Harness.isStub("Quest04Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Spell:", "Fair:", "Hit for:", "Grub now:" };
            int[] points = { 0, 0, 0 };
            String[][] names = {
                { "cost 2, one square away", "cost 2, one square away" },
                { "cost 2, four squares away", "cost 2, four squares away" },
                { "cost 1, two squares away", "cost 1, two squares away" },
            };
            String[][] inputs = {
                { "2", "5", "2", "1", "9" },
                { "2", "5", "2", "4", "9" },
                { "1", "4", "1", "2", "3" },
            };
            String[][] expected = {
                { "zap (2 mana)", "true", "5", "4" },
                { "zap (2 mana)", "true", "2", "7" },
                { "zap (1 mana)", "false", "4", "0" },
            };
            Cases.run(c, "Quest04Challenge", labels, points, names, inputs, expected);
        }
    }
}
