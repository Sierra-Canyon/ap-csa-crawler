import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * AP-SUBSET-SCAN: SKIP (this is the checker, not student code)
 *
 * A table of cases for one program. Each case is some input and the values expected after each label.
 *
 * A label that ends in ~ holds a decimal number, and anything within 0.01 passes.
 * A label that ends in * is printed more than once, and every value is collected in order.
 */
public class Cases {

    /**
     * names[i] is { the case's own name, the name of the check it belongs to }.
     * points[i] of -1 means "part of the same check as the row above": the check passes when every row in it does.
     */
    public static void run(Check c, String className, String[] labels, int[] points, String[][] names, String[][] inputs, String[][] expected) {
        int i = 0;
        while (i < inputs.length) {
            int end = i + 1;
            while (end < inputs.length && points[end] == -1) end++;
            String item = names[i].length > 1 && end - i > 1 ? names[i][1] : names[i][0];
            String problem = "";
            for (int k = i; k < end && problem.isEmpty(); k++) {
                try {
                    String out = Harness.run(className, String.join("\n", inputs[k]) + "\n");
                    problem = compare(out, labels, expected[k]);
                } catch (Harness.NotRunnable e) {
                    problem = e.getMessage();
                }
                if (!problem.isEmpty() && end - i > 1) problem = "in the row \"" + names[k][0] + "\": " + problem;
            }
            if (problem.isEmpty()) c.pass(item, points[i]); else c.fail(item, points[i], problem);
            i = end;
        }
    }

    /**
     * Every value printed after the label, in order. A prompt written with print and no newline leaves the
     * cursor on the line, so the label is looked for anywhere on a line, and the LAST place it appears on
     * that line is the one that counts: "Hero: Health: Hero: Rook (10/10 health, attack 4)" reads as Rook.
     */
    public static List<String> values(String out, String label) {
        List<String> found = new java.util.ArrayList<>();
        for (String line : out.split("\\R")) {
            int at = line.lastIndexOf(label);
            if (at >= 0) found.add(line.substring(at + label.length()).trim());
        }
        return found;
    }

    /** "" when every label has the expected value. Otherwise the first difference, in words. */
    public static String compare(String out, String[] labels, String[] expected) {
        for (int k = 0; k < labels.length; k++) {
            String label = labels[k];
            if (label.endsWith("*")) {
                label = label.substring(0, label.length() - 1);
                List<String> got = values(out, label);
                String joined = String.join(" | ", got);
                if (!joined.equals(expected[k])) return "every " + label + " line, expected: " + expected[k] + "\n              actual:   " + (got.isEmpty() ? "(no line with that label)" : joined);
                continue;
            }
            boolean decimal = label.endsWith("~");
            if (decimal) label = label.substring(0, label.length() - 1);
            List<String> all = values(out, label);
            if (all.isEmpty()) return "there is no line with the label " + label;
            String got = all.get(all.size() - 1);      // a program may print a label along the way. The last one is its answer.
            if (decimal) {
                try {
                    if (Math.abs(Double.parseDouble(got) - Double.parseDouble(expected[k])) > 0.01) return label + " expected about " + expected[k] + ", got " + got;
                } catch (NumberFormatException e) {
                    return label + " expected a number near " + expected[k] + ", got " + got;
                }
            } else if (!got.equals(expected[k])) {
                return label + " expected: " + expected[k] + "\n              actual:   " + got;
            }
        }
        return "";
    }

    /** How many times the text appears in the program's own code (comments and strings taken out). */
    public static int count(String className, String text) {
        String src = code(className), t = text.replaceAll("\\s+", "");
        int n = 0;
        for (int at = src.indexOf(t); at >= 0; at = src.indexOf(t, at + t.length())) n++;
        return n;
    }

    /** True when the program's own code (comments and strings taken out) contains the text. */
    public static boolean uses(String className, String text) {
        return code(className).contains(text.replaceAll("\\s+", ""));
    }

    private static String code(String className) {
        String src;
        try {
            src = Files.readString(Path.of("src", className + ".java"));
        } catch (Exception e) {
            return "";
        }
        // One pass, left to right, so that a quote inside a comment or a // inside a string cannot confuse it.
        StringBuilder kept = new StringBuilder();
        int i = 0, n = src.length();
        while (i < n) {
            char ch = src.charAt(i);
            if (src.startsWith("//", i)) {
                while (i < n && src.charAt(i) != '\n') i++;
            } else if (src.startsWith("/*", i)) {
                int end = src.indexOf("*/", i + 2);
                i = end < 0 ? n : end + 2;
            } else if (ch == '"' || ch == '\'') {
                i++;
                while (i < n && src.charAt(i) != ch && src.charAt(i) != '\n') i += src.charAt(i) == '\\' ? 2 : 1;
                i++;
                kept.append(ch).append(ch);
            } else {
                if (!Character.isWhitespace(ch)) kept.append(ch);
                i++;
            }
        }
        return kept.toString();
    }
}
