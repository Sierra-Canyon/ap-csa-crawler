/** Quest 1: Roll for Damage, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest01Test {
    public static void run(Check c) {
        c.begin("Quest 1: Roll for Damage");
        if (Harness.isStub("Quest01RollForDamage")) { c.skip("all checks", 5, "not started"); } else {
            String[] labels = { "Damage:", "Swapped:", "Steps:", "Straight line:~" };
            int[] points = { 1, 1, 1, 1 };
            String[][] names = {
                { "attack 4, defense 1", "attack 4, defense 1" },
                { "attack 2, defense 5", "attack 2, defense 5" },
                { "attack 5, defense 0", "attack 5, defense 0" },
                { "attack 3, defense 3, one square away diagonally", "attack 3, defense 3, one square away diagonally" },
            };
            String[][] inputs = {
                { "4", "1", "1", "1", "4", "5" },
                { "2", "5", "0", "0", "0", "3" },
                { "5", "0", "6", "2", "1", "2" },
                { "3", "3", "2", "7", "3", "8" },
            };
            String[][] expected = {
                { "3", "1", "7", "5.0" },
                { "1", "3", "3", "3.0" },
                { "5", "1", "5", "5.0" },
                { "1", "1", "2", "1.41" },
            };
            Cases.run(c, "Quest01RollForDamage", labels, points, names, inputs, expected);
            try {
                java.util.List<String> seen = new java.util.ArrayList<>();
                String wrong = "";
                for (int run = 0; run < 40; run++) {
                    String roll = Harness.line(Harness.run("Quest01RollForDamage", "4\n1\n1\n1\n4\n5\n"), "Roll:");
                    if (roll == null || !roll.matches("[1-6]")) wrong = "one run printed Roll: " + roll;
                    else if (!seen.contains(roll)) seen.add(roll);
                }
                if (!wrong.isEmpty()) c.fail("Roll is always from 1 to 6", 1, wrong);
                else c.isTrue("Roll is always from 1 to 6", 1, seen.size() >= 3, "40 runs only ever rolled " + seen);
            } catch (Harness.NotRunnable e) {
                c.fail("Roll is always from 1 to 6", 1, e.getMessage());
            }
        }
        c.begin("Quest 1 challenge: Any Dice");
        if (Harness.isStub("Quest01Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Values:" };
            int[] points = { 0, 0, 0 };
            String[][] names = {
                { "3 to 5", "3 to 5" },
                { "1 to 20", "1 to 20" },
                { "4 to 4", "4 to 4" },
            };
            String[][] inputs = {
                { "3", "5" },
                { "1", "20" },
                { "4", "4" },
            };
            String[][] expected = {
                { "3" },
                { "20" },
                { "1" },
            };
            Cases.run(c, "Quest01Challenge", labels, points, names, inputs, expected);
            try {
                java.util.List<String> seen = new java.util.ArrayList<>();
                String wrong = "";
                for (int run = 0; run < 200; run++) {
                    String roll = Harness.line(Harness.run("Quest01Challenge", "3\n5\n"), "Roll:");
                    if (roll == null || !roll.matches("[345]")) wrong = "one run printed Roll: " + roll;
                    else if (!seen.contains(roll)) seen.add(roll);
                }
                String four = Harness.line(Harness.run("Quest01Challenge", "4\n4\n"), "Roll:");
                if (!wrong.isEmpty()) c.fail("3 to 5, 200 runs", 0, wrong);
                else if (seen.size() < 3) c.fail("3 to 5, 200 runs", 0, "200 runs only ever rolled " + seen);
                else if (!"4".equals(four)) c.fail("4 to 4", 0, "printed Roll: " + four);
                else if (Cases.uses("Quest01Challenge", "Combat.roll")) c.fail("your own formula", 0, "this one is written without Combat.roll");
                else c.pass("every roll in range, every value seen", 0);
            } catch (Harness.NotRunnable e) {
                c.fail("3 to 5, 200 runs", 0, e.getMessage());
            }
        }
    }
}
