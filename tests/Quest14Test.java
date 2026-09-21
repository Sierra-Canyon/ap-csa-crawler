/** Quest 14: Mana and Turns, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest14Test {
    public static void run(Check c) {
        c.begin("Quest 14: Mana and Turns");
        if (Harness.isStub("Quest14ManaAndTurns")) { c.skip("all checks", 7, "not started"); } else {
            String[] labels = { "Turns:", "Casts:", "Refused:", "Mana left:" };
            int[] points = { 1, 1, 1, 1, 1, 1, 1 };
            String[][] names = {
                { "four sparks from full mana", "four sparks from full mana" },
                { "waiting for mana that is not enough", "waiting for mana that is not enough" },
                { "refused, then one more turn, then cast", "refused, then one more turn, then cast" },
                { "quit straight away", "quit straight away" },
                { "eight waits at full mana", "eight waits at full mana" },
                { "a cast can be the fourth turn", "a cast can be the fourth turn" },
                { "pay first, then gain", "pay first, then gain" },
            };
            String[][] inputs = {
                { "6", "spark", "spark", "spark", "spark", "quit" },
                { "0", "wait", "wait", "wait", "wait", "spark", "quit" },
                { "1", "wait", "wait", "wait", "spark", "wait", "spark", "quit" },
                { "6", "quit" },
                { "6", "wait", "wait", "wait", "wait", "wait", "wait", "wait", "wait", "quit" },
                { "5", "wait", "wait", "wait", "spark", "quit" },
                { "6", "wait", "wait", "wait", "spark", "quit" },
            };
            String[][] expected = {
                { "3", "3", "1", "0" },
                { "4", "0", "1", "1" },
                { "5", "1", "1", "0" },
                { "0", "0", "0", "6" },
                { "8", "0", "0", "6" },
                { "4", "1", "0", "4" },
                { "4", "1", "0", "5" },
            };
            Cases.run(c, "Quest14ManaAndTurns", labels, points, names, inputs, expected);
        }
        c.begin("Quest 14 challenge: The Longest Rest");
        if (Harness.isStub("Quest14Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Longest rest:" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "runs of 2 and 3", "runs of 2 and 3" },
                { "quit straight away", "quit straight away" },
                { "no waits", "no waits" },
                { "the longest run is the last thing", "the longest run is the last thing" },
            };
            String[][] inputs = {
                { "wait", "wait", "spark", "wait", "wait", "wait", "spark", "quit" },
                { "quit" },
                { "spark", "spark", "quit" },
                { "wait", "spark", "wait", "wait", "wait", "wait", "quit" },
            };
            String[][] expected = {
                { "3" },
                { "0" },
                { "0" },
                { "4" },
            };
            Cases.run(c, "Quest14Challenge", labels, points, names, inputs, expected);
        }
    }
}
