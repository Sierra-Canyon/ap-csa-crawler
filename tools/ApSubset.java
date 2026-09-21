import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Flags Java that is legal, works, and is not on the AP Computer Science A
 * subset.  This never fails a build.  It exists because a model will hand you
 * the whole language and the exam is a small corner of it, and because the
 * cheapest moment to find that out is before you have practised the habit.
 *
 * Everything below is drawn from the Java Quick Reference students get in May
 * and from the CED's exclusion statements.
 */
public class ApSubset {

    record Rule(Pattern pattern, String what, String instead) {}

    private static final List<Rule> RULES = List.of(
        new Rule(Pattern.compile("\\bcharAt\\s*\\("), "charAt",
                 "Use substring(i, i + 1) and compare with .equals()."),
        new Rule(Pattern.compile("\\bchar\\s+[A-Za-z_]"), "a char variable",
                 "Use a one-character String. The three primitive types in this course are int, double and boolean."),
        new Rule(Pattern.compile("\\bchar\\s*\\[\\s*\\]"), "a char array",
                 "Use a String, or an array of one-character Strings."),
        new Rule(Pattern.compile("\\blastIndexOf\\s*\\("), "lastIndexOf",
                 "indexOf(String) is the only one on the Quick Reference."),
        new Rule(Pattern.compile("\\bMath\\.(round|min|max|floor|ceil|log|sin|cos|tan)\\b"),
                 "a Math method that is not on the Quick Reference",
                 "abs, pow, sqrt and random are the four you get. Round with (int)(x + 0.5)."),
        new Rule(Pattern.compile("\\bhasNext(Int|Double|Line|Boolean)\\s*\\("),
                 "a hasNextSomething method",
                 "hasNext() is the only one on the Quick Reference."),
        new Rule(Pattern.compile("\\bprintf\\s*\\(|String\\.format\\s*\\("), "printf or String.format",
                 "Build the line with + instead."),
        new Rule(Pattern.compile("\\bStringBuilder\\b"), "StringBuilder",
                 "Use String concatenation. Slower, and the only one on the exam."),
        new Rule(Pattern.compile("\\b(HashMap|TreeMap|HashSet|Map|Set|LinkedList|Arrays|Collections)\\s*[<.]"),
                 "a collection that is not ArrayList",
                 "Use an array or an ArrayList. Those are the two data structures in this course."),
        new Rule(Pattern.compile("\\bpublic\\s+String\\s+toString\\s*\\("), "an override of toString",
                 "Write a method with a name of your own, such as describe()."),
        new Rule(Pattern.compile("\\bpublic\\s+boolean\\s+equals\\s*\\(\\s*Object"), "an override of equals",
                 "Write a method with a name of your own, such as sameAs(Pet other)."),
        new Rule(Pattern.compile("\\bextends\\b|\\bimplements\\b|\\binterface\\b|\\babstract\\b"),
                 "inheritance", "Use a single class. Inheritance was cut from this course in 2025."),
        new Rule(Pattern.compile("\\bswitch\\s*[({]"), "switch",
                 "Use if / else if / else."),
        new Rule(Pattern.compile("\\bdo\\s*\\{"), "a do-while loop",
                 "Use while or for. do-while is outside the scope of the exam."),
        // `throws FileNotFoundException` is required by the language for
        // Scanner(new File(...)), which IS in scope, so only try/catch is flagged.
        new Rule(Pattern.compile("\\btry\\s*\\{|\\bcatch\\s*\\("),
                 "exception handling", "Check first instead, as in if (d != null)."),
        new Rule(Pattern.compile("\\?[^:\"']*:"), "a ternary ?:",
                 "Use an if / else statement."),
        new Rule(Pattern.compile("(^|[^+])\\+\\+[A-Za-z_]"), "a prefix ++",
                 "Use x++ on a line of its own. Prefix increment is excluded."),
        new Rule(Pattern.compile("\\bSystem\\.exit\\s*\\("), "System.exit",
                 "Let main reach its closing brace instead.")
    );

    public static void main(String[] args) throws IOException {
        Path root = Path.of(args.length > 0 ? args[0] : "src");
        List<String> notes = new ArrayList<>();
        if (!Files.isDirectory(root)) { System.out.println("no " + root + " folder to scan"); return; }

        try (Stream<Path> files = Files.walk(root)) {
            for (Path f : files.filter(p -> p.toString().endsWith(".java")).sorted().toList()) {
                String src = Files.readString(f);
                if (src.contains("AP-SUBSET-SCAN: SKIP")) continue;
                String[] lines = src.split("\n", -1);
                for (int i = 0; i < lines.length; i++) {
                    String line = stripped(lines[i]);
                    if (line.isBlank()) continue;
                    for (Rule r : RULES) {
                        Matcher m = r.pattern().matcher(line);
                        if (m.find()) {
                            notes.add(f + ":" + (i + 1) + "  uses " + r.what()
                                      + "\n      Not on the AP subset. " + r.instead());
                            break;
                        }
                    }
                }
            }
        }

        if (notes.isEmpty()) {
            System.out.println("AP subset scan: clean.");
            return;
        }
        System.out.println("AP subset scan: " + notes.size()
                           + " thing" + (notes.size() == 1 ? "" : "s") + " to look at.");
        System.out.println("Nothing here is broken and nothing here fails the build. It is Java that");
        System.out.println("works and will not be available to you in May.");
        System.out.println();
        for (String n : notes) System.out.println("  " + n);
    }

    /** Blank out string literals and comments so their contents never match a rule. */
    private static String stripped(String line) {
        String s = line.replaceAll("\"([^\"\\\\]|\\\\.)*\"", "\"\"");
        int c = s.indexOf("//");
        if (c >= 0) s = s.substring(0, c);
        s = s.replaceAll("/\\*.*?\\*/", "");
        if (s.trim().startsWith("*")) return "";
        return s;
    }
}
