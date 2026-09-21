/** Quest 7: First Encounter, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest07Test {
    public static void run(Check c) {
        c.begin("Quest 7: First Encounter");
        if (Harness.isStub("Quest07FirstEncounter")) { c.skip("all checks", 5, "not started"); } else {
            String[] labels = { "Damage:", "Health left:", "Result:" };
            int[] points = { 1, 1, 1, 1, 1 };
            String[][] names = {
                { "roll 3, attack 4, monster health 9", "roll 3, attack 4, monster health 9" },
                { "roll 1, attack 4, monster health 9", "roll 1, attack 4, monster health 9" },
                { "roll 6, attack 4, monster health 9", "roll 6, attack 4, monster health 9" },
                { "roll 6, attack 3, monster health 5", "roll 6, attack 3, monster health 5" },
                { "roll 2, attack 3, monster health 3", "roll 2, attack 3, monster health 3" },
            };
            String[][] inputs = {
                { "Rook", "4", "Grub", "9", "3" },
                { "Rook", "4", "Grub", "9", "1" },
                { "Rook", "4", "Grub", "9", "6" },
                { "Mote", "3", "Cave Bat", "5", "6" },
                { "Mote", "3", "Cave Bat", "3", "2" },
            };
            String[][] expected = {
                { "4", "5", "hit" },
                { "0", "9", "miss" },
                { "8", "1", "critical" },
                { "6", "0", "defeated" },
                { "3", "0", "defeated" },
            };
            Cases.run(c, "Quest07FirstEncounter", labels, points, names, inputs, expected);
        }
        c.begin("Quest 7 challenge: What Is in the Chest");
        if (Harness.isStub("Quest07Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Loot:" };
            int[] points = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            String[][] names = {
                { "roll 1", "roll 1" },
                { "roll 3", "roll 3" },
                { "roll 4", "roll 4" },
                { "roll 5", "roll 5" },
                { "roll 6", "roll 6" },
                { "roll 7", "roll 7" },
                { "roll 8", "roll 8" },
                { "roll 9", "roll 9" },
                { "roll 10", "roll 10" },
                { "roll 0", "roll 0" },
                { "roll 11", "roll 11" },
                { "roll -4", "roll -4" },
            };
            String[][] inputs = {
                { "1" },
                { "3" },
                { "4" },
                { "5" },
                { "6" },
                { "7" },
                { "8" },
                { "9" },
                { "10" },
                { "0" },
                { "11" },
                { "-4" },
            };
            String[][] expected = {
                { "potion" },
                { "potion" },
                { "gold" },
                { "gold" },
                { "shield" },
                { "arrow" },
                { "arrow" },
                { "stone" },
                { "stone" },
                { "nothing" },
                { "nothing" },
                { "nothing" },
            };
            Cases.run(c, "Quest07Challenge", labels, points, names, inputs, expected);
            c.isTrue("no && and no ||", 0, !Cases.uses("Quest07Challenge", "&&") && !Cases.uses("Quest07Challenge", "||"), "the chain does not need && or ||");
        }
    }
}
