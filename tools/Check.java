import java.util.ArrayList;
import java.util.List;

/**
 * A very small test reporter.  No libraries, no downloads, no build tool:
 * it runs anywhere a JDK runs, including a school network that blocks
 * everything.  JUnit joins the party in January, when there are real methods
 * to test; see junit/README.md.
 */
public class Check {

    public static class Result {
        public final String assignment;
        public final String item;
        public final int points;
        public final boolean passed;
        public final boolean skipped;
        public final String detail;

        Result(String a, String i, int p, boolean ok, boolean sk, String d) {
            assignment = a; item = i; points = p; passed = ok; skipped = sk; detail = d;
        }
    }

    private final List<Result> results = new ArrayList<>();
    private String assignment = "";

    public void begin(String name) { assignment = name; }

    public List<Result> results() { return results; }

    /** Record a check that the student has not started yet. */
    public void skip(String item, int points, String why) {
        results.add(new Result(assignment, item, points, false, true, why));
    }

    public void pass(String item, int points) {
        results.add(new Result(assignment, item, points, true, false, ""));
    }

    public void fail(String item, int points, String detail) {
        results.add(new Result(assignment, item, points, false, false, detail));
    }

    /** Assert that `actual` equals `expected`, reporting both when it does not. */
    public void equal(String item, int points, String expected, String actual) {
        if (expected.equals(actual)) {
            pass(item, points);
        } else {
            fail(item, points, "expected: " + show(expected) + "\n              actual:   " + show(actual));
        }
    }

    public void equal(String item, int points, int expected, int actual) {
        equal(item, points, String.valueOf(expected), String.valueOf(actual));
    }

    /** Assert a double within a tolerance, for anything that came out of arithmetic. */
    public void near(String item, int points, double expected, double actual, double tol) {
        if (Math.abs(expected - actual) <= tol) {
            pass(item, points);
        } else {
            fail(item, points, "expected about " + expected + ", got " + actual);
        }
    }

    public void isTrue(String item, int points, boolean condition, String detail) {
        if (condition) pass(item, points); else fail(item, points, detail);
    }

    private static String show(String s) {
        if (s == null) return "(nothing)";
        return "\"" + s.replace("\n", "\\n") + "\"";
    }
}
