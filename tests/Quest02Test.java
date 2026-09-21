/** Quest 2: Meet the Party, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest02Test {
    public static void run(Check c) {
        c.begin("Quest 2: Meet the Party");
        if (Harness.isStub("Quest02MeetTheParty")) { c.skip("all checks", 3, "not started"); } else {
            String[] labels = { "Hero:", "Monster:", "Square:", "Boss:" };
            int[] points = { 1, 1, 1 };
            String[][] names = {
                { "Rook and a Grub", "Rook and a Grub" },
                { "Mote and a Bat", "Mote and a Bat" },
                { "Vex and an Ogre", "Vex and an Ogre" },
            };
            String[][] inputs = {
                { "Rook", "10", "4", "Grub", "4", "2", "3", "5" },
                { "Mote", "7", "3", "Bat", "3", "3", "1", "12" },
                { "Vex", "1", "5", "Ogre", "12", "6", "0", "0" },
            };
            String[][] expected = {
                { "Rook (10/10 health, attack 4)", "Grub (4 health, attack 2)", "3,5", "null" },
                { "Mote (7/7 health, attack 3)", "Bat (3 health, attack 3)", "1,12", "null" },
                { "Vex (1/1 health, attack 5)", "Ogre (12 health, attack 6)", "0,0", "null" },
            };
            Cases.run(c, "Quest02MeetTheParty", labels, points, names, inputs, expected);
        }
        c.begin("Quest 2 challenge: Two Ways to Make a Spell");
        if (Harness.isStub("Quest02Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "First:", "First kind:", "Second kind:", "Second at 1:", "Second at 5:", "First at 5:" };
            int[] points = { 0, 0 };
            String[][] names = {
                { "zap, cost 2", "zap, cost 2" },
                { "flare, cost 9", "flare, cost 9" },
            };
            String[][] inputs = {
                { "zap", "2", "5", "2" },
                { "flare", "9", "12", "3" },
            };
            String[][] expected = {
                { "zap (2 mana)", "spark", "bolt", "5", "2", "4" },
                { "flare (6 mana)", "spark", "bolt", "9", "3", "4" },
            };
            Cases.run(c, "Quest02Challenge", labels, points, names, inputs, expected);
        }
    }
}
