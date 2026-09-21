/** Quest 10: Boss Phase, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest10Test {
    public static void run(Check c) {
        c.begin("Quest 10: Boss Phase");
        if (Harness.isStub("Quest10BossPhase")) { c.skip("all checks", 8, "not started"); } else {
            String[] labels = { "Phase:", "Shockwave every:", "Harder hitter:" };
            int[] points = { 1, 1, 1, 1, 1, 1, 1 };
            String[][] names = {
                { "18 of 18", "18 of 18" },
                { "13 of 18", "13 of 18" },
                { "12 of 18, exactly two thirds", "12 of 18, exactly two thirds" },
                { "7 of 18", "7 of 18" },
                { "6 of 18, exactly one third", "6 of 18, exactly one third" },
                { "1 of 20", "1 of 20" },
                { "14 of 20", "14 of 20" },
            };
            String[][] inputs = {
                { "18", "18", "4", "5" },
                { "13", "18", "4", "5" },
                { "12", "18", "5", "5" },
                { "7", "18", "6", "5" },
                { "6", "18", "4", "7" },
                { "1", "20", "4", "4" },
                { "14", "20", "9", "1" },
            };
            String[][] expected = {
                { "1", "0", "boss" },
                { "1", "0", "boss" },
                { "2", "4", "tie" },
                { "2", "4", "hero" },
                { "3", "3", "boss" },
                { "3", "3", "tie" },
                { "1", "0", "hero" },
            };
            Cases.run(c, "Quest10BossPhase", labels, points, names, inputs, expected);
            c.isTrue("your own rule, not Combat's", 1, !Cases.uses("Quest10BossPhase", "Combat.bossPhase") && !Cases.uses("Quest10BossPhase", "Combat.shockwaveEvery"), "the file calls Combat.bossPhase or Combat.shockwaveEvery");
        }
        c.begin("Quest 10 challenge: Which Powerup");
        if (Harness.isStub("Quest10Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Powerup:", "Damage with it:" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "row 3, col 12, 5 turns", "row 3, col 12, 5 turns" },
                { "row 3, col 12, 0 turns", "row 3, col 12, 0 turns" },
                { "row 1, col 3", "row 1, col 3" },
                { "row 2, col 3", "row 2, col 3" },
            };
            String[][] inputs = {
                { "3", "12", "5", "4" },
                { "3", "12", "0", "4" },
                { "1", "3", "5", "4" },
                { "2", "3", "9", "1" },
            };
            String[][] expected = {
                { "might", "6" },
                { "might", "4" },
                { "guard", "4" },
                { "regen", "1" },
            };
            Cases.run(c, "Quest10Challenge", labels, points, names, inputs, expected);
            c.isTrue("your own rule, not Powerups.nameAt", 0, !Cases.uses("Quest10Challenge", "Powerups.nameAt"), "the file calls Powerups.nameAt");
        }
    }
}
