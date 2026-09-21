/** Quest 6: The Wall Bug, and its challenge. Built by teacher/tools/make_quests.py. */
public class Quest06Test {
    public static void run(Check c) {
        c.begin("Quest 6: The Wall Bug");
        if (Harness.isStub("Quest06TheWallBug")) { c.skip("all checks", 4, "not started"); } else {
            String[] labels = { "Tile:", "Wall by ==:", "Wall by equals:", "Compare:", "Row still:" };
            int[] points = { 1, 1, 1, 1 };
            String[][] names = {
                { "column 0 is a wall", "column 0 is a wall" },
                { "column 3 is a monster", "column 3 is a monster" },
                { "column 4 is the door", "column 4 is the door" },
                { "a row one tile long", "a row one tile long" },
            };
            String[][] inputs = {
                { "#.@M$#", "0", "Rook", "Mote" },
                { "#.@M$#", "3", "Mote", "Rook" },
                { "##..+", "4", "Rook", "Rook" },
                { "#", "0", "Rook", "Rookie" },
            };
            String[][] expected = {
                { "[#]", "false", "true", "5", "#.@M$#" },
                { "[M]", "false", "false", "-5", "#.@M$#" },
                { "[+]", "false", "false", "0", "##..+" },
                { "[#]", "false", "true", "-2", "#" },
            };
            Cases.run(c, "Quest06TheWallBug", labels, points, names, inputs, expected);
        }
        c.begin("Quest 6 challenge: Clear the Square");
        if (Harness.isStub("Quest06Challenge")) { c.skip("all checks", 0, "not started"); } else {
            String[] labels = { "Before:", "After:", "Before again:" };
            int[] points = { 0, 0, 0 };
            String[][] names = {
                { "a monster in the middle", "a monster in the middle" },
                { "the first square", "the first square" },
                { "the last square", "the last square" },
            };
            String[][] inputs = {
                { "#.@M$#", "3" },
                { "M..#", "0" },
                { "#..M", "3" },
            };
            String[][] expected = {
                { "#.@M$#", "#.@.$#", "#.@M$#" },
                { "M..#", "...#", "M..#" },
                { "#..M", "#...", "#..M" },
            };
            Cases.run(c, "Quest06Challenge", labels, points, names, inputs, expected);
        }
    }
}
