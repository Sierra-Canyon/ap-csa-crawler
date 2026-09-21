/** Quest 11: Battle Loop, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest11Test {
    public static void run(Check c) {
        c.begin("Quest 11: Battle Loop");
        if (Harness.isStub("Quest11BattleLoop")) { c.skip("all checks", 5, "not started"); } else {
            String[] labels = { "Rounds:", "Winner:", "Rook now:", "Grub now:" };
            int[] points = { 1, 1, 1, 1, 1 };
            String[][] names = {
                { "10 and 4 against 9 and 3", "10 and 4 against 9 and 3" },
                { "5 and 1 against 20 and 3", "5 and 1 against 20 and 3" },
                { "over in one hit", "over in one hit" },
                { "Rook falls in round one", "Rook falls in round one" },
                { "two attacks of 0", "two attacks of 0" },
            };
            String[][] inputs = {
                { "10", "4", "9", "3" },
                { "5", "1", "20", "3" },
                { "10", "4", "4", "9" },
                { "3", "2", "5", "3" },
                { "10", "0", "3", "0" },
            };
            String[][] expected = {
                { "3", "Rook", "4", "0" },
                { "2", "Grub", "0", "18" },
                { "1", "Rook", "10", "0" },
                { "1", "Grub", "0", "3" },
                { "3", "Rook", "8", "0" },
            };
            Cases.run(c, "Quest11BattleLoop", labels, points, names, inputs, expected);
        }
        c.begin("Quest 11 challenge: Drink at Three");
        if (Harness.isStub("Quest11Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Rounds:", "Winner:", "Potions left:", "Rook now:" };
            int[] points = { 0, 0, 0, 0 };
            String[][] names = {
                { "8 and 3 against 12 and 3, two potions", "8 and 3 against 12 and 3, two potions" },
                { "4 and 1 against 30 and 3, one potion", "4 and 1 against 30 and 3, one potion" },
                { "never needs one", "never needs one" },
                { "a round that starts on exactly 3", "a round that starts on exactly 3" },
            };
            String[][] inputs = {
                { "8", "3", "12", "3", "2" },
                { "4", "1", "30", "3", "1" },
                { "10", "5", "5", "1", "3" },
                { "9", "1", "30", "3", "1" },
            };
            String[][] expected = {
                { "6", "Rook", "0", "3" },
                { "3", "Grub", "0", "0" },
                { "1", "Rook", "3", "10" },
                { "5", "Grub", "0", "0" },
            };
            Cases.run(c, "Quest11Challenge", labels, points, names, inputs, expected);
        }
    }
}
