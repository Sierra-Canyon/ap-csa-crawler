import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * AP-SUBSET-SCAN: SKIP (this is the checker, not student code)
 *
 * Added 10 October 2026. The Yours rows of Quests 11 to 17 and the project. A Yours part is the student's own
 * idea: their monster, their bar, their verdict. These rows check its SHAPE, never its words, so they work
 * on anything a student invents. Each quest's test calls one method here with the table it already has
 * (inputs and expected values) and the names of its Yours rows, which are printed on the sheet.
 *
 * Where the answer depends on the student's own content (their level, their dungeon), the expected values
 * come from Referee, inside given/crawler.jar.
 */
public class YoursCheck {

    /** The program's output for every input, in order. */
    static String[] outputs(String cls, String[][] inputs) throws Harness.NotRunnable {
        String[] out = new String[inputs.length];
        for (int k = 0; k < inputs.length; k++) out[k] = Harness.run(cls, String.join("\n", inputs[k]) + "\n");
        return out;
    }

    static String last(String out, String label) {
        List<String> v = Cases.values(out, label);
        return v.isEmpty() ? null : v.get(v.size() - 1);
    }

    /** The words of a line with every number taken out, so "3 hits, a rout" and "5 hits, a rout" count as one saying. */
    static String words(String s) {
        String w = s.replaceAll("-?[0-9]+(\\.[0-9]+)?", "#").replaceAll("\\s+", " ").trim().toLowerCase();
        return w.replaceAll("# ([a-z]+?)s\\b", "# $1");   // "1 hit" and "3 hits" are the same saying
    }

    /** True when the number appears in the line as a number of its own: 12 is in "hit 12 times", not in "112". */
    static boolean hasNumber(String line, String number) {
        return Pattern.compile("(?<![0-9.])" + Pattern.quote(number) + "(?![0-9])").matcher(line).find();
    }

    static void row(Check c, String name, String problem) {
        if (problem.isEmpty()) c.pass(name, 1); else c.fail(name, 1, problem);
    }

    /** Runs the program on every input and hands each row its problem, or the run's problem to all of them. */
    interface Rows { String[] check(String[] outs) throws Harness.NotRunnable; }

    static void rows(Check c, String cls, String[][] inputs, String[] names, Rows rows) {
        String[] problems;
        try {
            problems = rows.check(outputs(cls, inputs));
        } catch (Harness.NotRunnable e) {
            problems = new String[names.length];
            for (int i = 0; i < names.length; i++) problems[i] = e.getMessage();
        }
        if (Cases.uses(cls, "Referee")) for (int i = 0; i < names.length; i++) problems[i] = "the program calls Referee, which is the checker's, not yours";
        for (int i = 0; i < names.length; i++) row(c, names[i], problems[i]);
    }

    /** The same non-empty value after the label in every output, or a problem. */
    static String same(String[] outs, String label, String[] value) {
        for (int k = 0; k < outs.length; k++) {
            String v = last(outs[k], label);
            if (v == null || v.isEmpty()) return "there is no line with the label " + label + " in case " + (k + 1);
            if (value[0] == null) value[0] = v;
            else if (!v.equals(value[0])) return label + " should be the same in every case, but it was " + value[0] + " and then " + v;
        }
        return "";
    }

    // ------------------------------------------------------------------ Quest 11: your monster, and a line for every round

    public static void quest11(Check c, String cls, String[][] inputs, String[][] expected, String[] names) {
        rows(c, cls, inputs, names, outs -> {
            String[] v = { null };
            String one = same(outs, "Your monster:", v);
            if (one.isEmpty()) {
                String[] w = v[0].split("\\s+");
                if (w.length != 3) one = "Your monster: should be a name, a health, and an attack, with a space between, such as Gloomcap 9 2. It was " + v[0];
                else if (!w[0].matches("[A-Za-z]+")) one = "the monster's name should be one word of letters, but it was " + w[0];
                else if (w[0].equalsIgnoreCase("Grub") || w[0].equalsIgnoreCase("Rook")) one = "the monster should be one you invent, not " + w[0];
                else if (!w[1].matches("[0-9]+") || Integer.parseInt(w[1]) < 2 || Integer.parseInt(w[1]) > 12) one = "the health should be a whole number from 2 to 12, but it was " + w[1];
                else if (!w[2].matches("[0-9]+") || Integer.parseInt(w[2]) < 1 || Integer.parseInt(w[2]) > 4) one = "the attack should be a whole number from 1 to 4, but it was " + w[2];
            }
            String two = "";
            for (int k = 0; k < outs.length && two.isEmpty(); k++) {
                List<String> lines = Cases.values(outs[k], "Round:");
                int rounds = Integer.parseInt(expected[k][0]);
                if (lines.size() != rounds) two = "case " + (k + 1) + " has " + rounds + " rounds but " + lines.size() + " lines labeled Round:";
                for (int j = 0; j < lines.size() && two.isEmpty(); j++)
                    if (!hasNumber(lines.get(j), "" + (j + 1))) two = "in case " + (k + 1) + ", Round: line " + (j + 1) + " does not say its round number, " + (j + 1) + ": " + lines.get(j);
            }
            return new String[] { one, two };
        });
    }

    // ------------------------------------------------------------------ Quest 12: your bar

    public static void quest12(Check c, String cls, String[][] inputs, String[][] expected, String[] names) {
        rows(c, cls, inputs, names, outs -> {
            String[] bars = new String[outs.length];
            for (int k = 0; k < outs.length; k++) {
                bars[k] = last(outs[k], "Your bar:");
                if (bars[k] == null) { String p = "there is no line with the label Your bar: in case " + (k + 1); return new String[] { p, p }; }
            }
            String full = null, empty = null, left = null, right = null;
            for (int k = 0; k < outs.length; k++) {
                int h = Integer.parseInt(inputs[k][0]), max = Integer.parseInt(inputs[k][1]);
                if (bars[k].length() != max + 2) { String p = "in case " + (k + 1) + " Your bar: should be " + (max + 2) + " characters, two ends and one for each point of full health, but it is " + bars[k]; return new String[] { p, p }; }
                if (left == null) { left = bars[k].substring(0, 1); right = bars[k].substring(max + 1); }
                if (h == max && full == null) full = bars[k].substring(1, 2);
                if (h == 0 && empty == null) empty = bars[k].substring(1, 2);
            }
            String one = "";
            for (int k = 0; k < outs.length && one.isEmpty(); k++) {
                int h = Integer.parseInt(inputs[k][0]), max = Integer.parseInt(inputs[k][1]);
                String want = left;
                for (int i = 0; i < max; i++) want += i < h ? full : empty;
                want += right;
                if (!bars[k].equals(want)) one = "in case " + (k + 1) + ", " + h + " of " + max + ", your design gives " + want + " but Your bar: was " + bars[k];
            }
            String two = "";
            if (full.equals(empty)) two = "a point the hero has and a point that is gone are both " + full + ". They need different characters";
            else if (full.equals("#") || full.equals(".") || empty.equals("#") || empty.equals(".")) two = "your two characters are " + full + " and " + empty + ". Neither may be # or ., which are the game's";
            else if (full.equals(" ") || empty.equals(" ")) two = "a space cannot be one of your characters";
            return new String[] { one, two };
        });
    }

    // ------------------------------------------------------------------ Quest 13: your verdict

    public static void quest13(Check c, String cls, String[][] inputs, String[][] expected, String[] names) {
        // The table's cases, then fights with averages 0, 0.5, 1, ... 9.5. Each whole-number average also comes as a
        // fight with one hit and two misses, so a chain on the number of hits instead of the average shows up.
        List<String[]> all = new ArrayList<>();
        List<Integer> twiceAverage = new ArrayList<>();
        for (String[] in : inputs) all.add(in);
        for (int v = 0; v <= 9; v++) {
            all.add(new String[] { "2", "" + v, "" + v }); twiceAverage.add(2 * v);
            all.add(new String[] { "2", "" + v, "" + (v + 1) }); twiceAverage.add(2 * v + 1);
            if (v > 0) { all.add(new String[] { "3", "" + (3 * v), "0", "0" }); twiceAverage.add(2 * v); }
        }
        rows(c, cls, all.toArray(new String[0][]), names, outs -> {
            String one = "";
            for (int k = 0; k < outs.length && one.isEmpty(); k++) {
                String verdict = last(outs[k], "Verdict:"), hits = last(outs[k], "Hits:");
                String in = String.join(" ", all.get(k));
                if (verdict == null || verdict.isEmpty()) one = "there is no Verdict: line for the input " + in;
                else if (hits == null) one = "there is no Hits: line for the input " + in;
                else if (!hasNumber(verdict, hits)) one = "for the input " + in + " Hits is " + hits + ", and the verdict does not say it: " + verdict;
            }
            String two = "";
            if (one.isEmpty()) {
                Map<Integer, String> byAverage = new java.util.TreeMap<>();
                for (int k = inputs.length; k < outs.length && two.isEmpty(); k++) {
                    String w = words(last(outs[k], "Verdict:"));
                    int key = twiceAverage.get(k - inputs.length);
                    if (byAverage.containsKey(key) && !byAverage.get(key).equals(w)) two = "two fights with the same average, " + (key / 2.0) + ", got different verdicts: \"" + byAverage.get(key) + "\" and \"" + w + "\". The average alone should pick the verdict";
                    byAverage.putIfAbsent(key, w);
                }
                List<String> seen = new ArrayList<>();
                String prev = null;
                for (String w : byAverage.values()) {
                    if (!two.isEmpty()) break;
                    if (!w.equals(prev)) {
                        if (seen.contains(w)) two = "as the average goes up, the verdict \"" + w + "\" comes back after a different one. Each verdict should cover one range of averages";
                        seen.add(w);
                        prev = w;
                    }
                }
                if (two.isEmpty() && seen.size() < 3) two = "averages from 0 to 9.5 gave " + seen.size() + " different verdict(s) (numbers left out): " + seen + ". You need at least 3";
            } else two = "the row above has to pass first";
            return new String[] { one, two };
        });
    }

    // ------------------------------------------------------------------ Quest 14: your command

    /** { Turns, Mana left, Casts } for a gain of 1, 2 and 3, one line per probe below. Worked out by hand and by the reference solution. */
    private static final String[][][] Q14 = {
        { { "3", "0", "1" }, { "3", "2", "1" }, { "3", "4", "1" } },     // 0, word, word, spark
        { { "5", "1", "1" }, { "5", "2", "1" }, { "5", "3", "1" } },     // 1, wait, wait, word, wait (the fourth-turn gain), spark
        { { "2", "6", "0" }, { "2", "6", "0" }, { "2", "6", "0" } },     // 4, word, word: never above 6, and not a cast
        { { "1", "6", "0" }, { "1", "6", "0" }, { "1", "6", "0" } },     // 5, word: never above 6
    };

    /** Words the text game already uses, so a student's command named like one of them would never reach their code. */
    public static final String[] TAKEN = { "wait", "spark", "quit", "help", "spells", "potion", "lead", "fire", "throw", "cast", "art" };

    public static void quest14(Check c, String cls, String[][] inputs, String[][] expected, String[] names) {
        String[] word = { null };
        String[] gainText = { null };
        rows(c, cls, inputs, names, outs -> {
            String[] v = { null };
            String one = same(outs, "Your command:", v);
            int gain = -1;
            if (one.isEmpty()) {
                String[] w = v[0].split("\\s+");
                if (w.length != 2) one = "Your command: should be a word and a gain, such as focus 2. It was " + v[0];
                else if (!w[0].matches("[a-z][a-z]+")) one = "your command should be one lowercase word of at least two letters, but it was " + w[0];
                else if (java.util.Arrays.asList(TAKEN).contains(w[0])) one = "the game already uses " + w[0] + ". Your command has to be a new word";
                else if (!w[1].matches("[0-9]+") || Integer.parseInt(w[1]) < 1 || Integer.parseInt(w[1]) > 3) one = "the gain should be 1, 2, or 3, but it was " + w[1];
                else { word[0] = w[0]; gain = Integer.parseInt(w[1]); }
            }
            String two = one.isEmpty() ? "" : "the row above has to pass first";
            if (two.isEmpty()) {
                String x = word[0];   // probes that exercise the student's own word; Q14 above has the answers
                String[][] probes = {
                    { "0", x, x, "spark", "quit" },
                    { "1", "wait", "wait", x, "wait", "spark", "quit" },
                    { "4", x, x, "quit" },
                    { "5", x, "quit" },
                };
                String[] labels = { "Turns:", "Mana left:", "Casts:" };
                for (int p = 0; p < probes.length && two.isEmpty(); p++) {
                    String out = Harness.run(cls, String.join("\n", probes[p]) + "\n");
                    String problem = Cases.compare(out, labels, Q14[p][gain - 1]);
                    if (!problem.isEmpty()) two = "with the commands " + String.join(" ", probes[p]) + ": " + problem;
                }
            }
            return new String[] { one, two };
        });
    }

    // ------------------------------------------------------------------ Quest 15: your level, read by your program

    /** The rows of a my/ file: no blank lines, no // comments. */
    static List<String> fileRows(String path) {
        List<String> rows = new ArrayList<>();
        try {
            for (String l : Files.readAllLines(Path.of(path))) if (!l.isBlank() && !l.trim().startsWith("//")) rows.add(l.stripTrailing());
        } catch (Exception e) {
            return rows;
        }
        return rows;
    }

    /** "" when the map is a real design: big enough, walls round the edge, at least two monsters and two gold. */
    static String design(List<String> rows, int wide, int tall, String tiles) {
        if (rows.isEmpty()) return "it has no rows";
        int width = rows.get(0).length();
        for (int r = 0; r < rows.size(); r++) if (rows.get(r).length() != width) return "row " + r + " is " + rows.get(r).length() + " long but row 0 is " + width;
        if (width < wide || rows.size() < tall) return "it is " + width + " wide and " + rows.size() + " tall. It needs to be at least " + wide + " wide and " + tall + " tall";
        int hero = 0, monsters = 0, gold = 0, doors = 0;
        for (int r = 0; r < rows.size(); r++)
            for (int col = 0; col < width; col++) {
                String t = rows.get(r).substring(col, col + 1);
                if (!tiles.contains(t)) return "row " + r + ", column " + col + " is '" + t + "'. The tiles are " + tiles;
                boolean edge = r == 0 || col == 0 || r == rows.size() - 1 || col == width - 1;
                if (edge && !t.equals("#") && !t.equals("+")) return "row " + r + ", column " + col + " is on the edge, so it has to be a wall # or a door +";
                if (t.equals("@")) hero++;
                if (t.equals("M")) monsters++;
                if (t.equals("$")) gold++;
                if (t.equals("+")) doors++;
            }
        if (hero != 1) return "it needs exactly one @, and it has " + hero;
        if (doors < 1) return "it has no door +";
        if (monsters < 2 || gold < 2) return "it has " + monsters + " M and " + gold + " $. It needs at least 2 of each";
        return "";
    }

    public static void quest15(Check c, String cls, String[][] inputs, String[][] expected, String[] names) {
        List<String> rows = fileRows("my/level.txt");
        String one = design(rows, 8, 5, Game.TILES);
        if (one.isEmpty()) for (String p : Game.checkLevel(rows)) { one = "the game says: " + p; break; }
        String two;
        if (!one.isEmpty()) two = "the row above has to pass first";
        else {
            int width = rows.get(0).length();
            String map = String.join("", rows);
            try {
                String out = Harness.run(cls, width + "\n" + map + "\n");
                String[] labels = { "Gold:", "Monsters:", "Hero row:", "Hero col:" };
                two = Cases.compare(out, labels, Referee.readTheMap(width, map));
                if (!two.isEmpty()) two = "on your level: " + two;
            } catch (Harness.NotRunnable e) {
                two = e.getMessage();
            }
        }
        if (Cases.uses(cls, "Referee")) two = "the program calls Referee, which is the checker's, not yours";
        row(c, names[0], one.isEmpty() ? "" : "my/level.txt: " + one);
        row(c, names[1], two);
    }

    // ------------------------------------------------------------------ Quest 16: your sayings

    public static void quest16(Check c, String cls, String[][] inputs, String[][] expected, String[] names) {
        rows(c, cls, inputs, names, outs -> {
            String one = "";
            Map<String, String> saying = new LinkedHashMap<>();
            String two = "";
            for (int k = 0; k < outs.length && one.isEmpty(); k++) {
                String says = last(outs[k], "Says:"), shot = expected[k][0], at = expected[k][1];
                if (says == null || says.isEmpty()) { one = "there is no Says: line in case " + (k + 1); break; }
                if (!shot.equals("falls short") && !hasNumber(says, at)) one = "in case " + (k + 1) + " the shot " + shot + " at column " + at + ", and Says: does not give the column: " + says;
                String w = words(says);
                if (saying.containsKey(shot) && !saying.get(shot).equals(w) && two.isEmpty()) two = "two shots that both " + (shot.equals("falls short") ? "fall short" : shot.equals("hits") ? "hit" : "are blocked") + " got different sayings: \"" + saying.get(shot) + "\" and \"" + w + "\"";
                saying.putIfAbsent(shot, w);
            }
            if (!one.isEmpty()) two = "the row above has to pass first";
            else if (two.isEmpty() && new java.util.HashSet<>(saying.values()).size() < saying.size()) two = "a hit, a block, and a shot that falls short need three different sayings (numbers left out). Yours: " + saying;
            return new String[] { one, two };
        });
    }

    // ------------------------------------------------------------------ Quest 17: your tile art

    public static void quest17(Check c, String cls, String[][] inputs, String[][] expected, String[] names) {
        rows(c, cls, inputs, names, outs -> {
            Map<String, String> art = new HashMap<>();
            String one = "";
            for (int k = 0; k < outs.length && one.isEmpty(); k++) {
                int width = Integer.parseInt(inputs[k][0]);
                String map = inputs[k][1];
                List<String> lines = Cases.values(outs[k], "Art:");
                if (lines.size() != map.length() / width) { one = "case " + (k + 1) + " has " + map.length() / width + " rows but " + lines.size() + " lines labeled Art:"; break; }
                for (int r = 0; r < lines.size() && one.isEmpty(); r++) {
                    if (lines.get(r).length() != width) { one = "in case " + (k + 1) + ", Art: row " + r + " should be " + width + " characters with no spaces, but it is " + lines.get(r); break; }
                    for (int col = 0; col < width && one.isEmpty(); col++) {
                        String tile = map.substring(r * width + col, r * width + col + 1), drawn = lines.get(r).substring(col, col + 1);
                        if (drawn.equals(" ")) one = "in case " + (k + 1) + ", Art: row " + r + " has a space in it";
                        else if (art.containsKey(tile) && !art.get(tile).equals(drawn)) one = "the tile " + tile + " is drawn as " + art.get(tile) + " in one place and " + drawn + " in another";
                        else art.put(tile, drawn);
                    }
                }
            }
            if (one.isEmpty()) {
                for (String a : art.keySet()) for (String b : art.keySet())
                    if (one.isEmpty() && a.compareTo(b) < 0 && art.get(a).equals(art.get(b))) one = "the tiles " + a + " and " + b + " are both drawn as " + art.get(a) + ". Different tiles need different characters";
            }
            String two = "";
            if (!one.isEmpty()) two = "the row above has to pass first";
            else if ("#".equals(art.get("#")) || ".".equals(art.get("."))) two = "the wall is drawn as " + art.get("#") + " and the floor as " + art.get(".") + ". Neither may be drawn as itself";
            return new String[] { one, two };
        });
    }

    // ------------------------------------------------------------------ The project: your dungeon

    public static void project(Check c, String cls, String name, int points) {
        List<String> lines = fileRows("my/dungeon.txt");
        List<String> rows = new ArrayList<>();
        int health = -1;
        String walk = null;
        for (String l : lines) {
            String t = l.trim();
            if (t.toUpperCase().startsWith("HEALTH ")) health = Integer.parseInt(t.substring(7).trim().matches("[0-9]+") ? t.substring(7).trim() : "-1");
            else if (t.toUpperCase().startsWith("WALK ")) walk = t.substring(5).trim();
            else rows.add(t);
        }
        String problem = design(rows, 8, 5, "#.@M$+");
        if (!problem.isEmpty()) problem = "my/dungeon.txt: " + problem;
        else if (health < 1 || health > 20) problem = "my/dungeon.txt needs a line HEALTH with a number from 1 to 20";
        else if (walk == null || walk.isEmpty()) problem = "my/dungeon.txt needs a line WALK with the commands that win it";
        if (problem.isEmpty()) {
            List<String> cmds = new ArrayList<>(List.of(walk.split("\\s+")));
            if (!cmds.contains("quit")) cmds.add("quit");
            int width = rows.get(0).length();
            String map = String.join("", rows);
            String[] want = Referee.project(width, map, health, cmds.toArray(new String[0]));
            if (!want[0].equals("won")) problem = "your WALK does not win your dungeon. By the rules the result is " + want[0] + ", with " + want[4] + " monster(s) left and the hero at " + want[5];
            else {
                List<String> input = new ArrayList<>(List.of("" + width, map, "" + health));
                input.addAll(cmds);
                try {
                    String out = Harness.run(cls, String.join("\n", input) + "\n");
                    String[] labels = { "Result:", "Gold:", "Health left:", "Steps:", "Monsters left:", "Hero at:", "Final map:*" };
                    String[] expect = new String[7];
                    System.arraycopy(want, 0, expect, 0, 7);
                    problem = Cases.compare(out, labels, expect);
                    if (!problem.isEmpty()) problem = "on your dungeon: " + problem;
                } catch (Harness.NotRunnable e) {
                    problem = e.getMessage();
                }
            }
        }
        if (Cases.uses(cls, "Referee")) problem = "the program calls Referee, which is the checker's, not yours";
        if (problem.isEmpty()) c.pass(name, points); else c.fail(name, points, problem);
    }
}
