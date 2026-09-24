import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Runs a student program's main method with a given standard input and hands
 * back whatever it printed.  Everything the tests know about a program, they
 * learn through here.
 */
public class Harness {

    /** Thrown when a program cannot be run at all, as opposed to running wrong. */
    public static class NotRunnable extends Exception {
        public NotRunnable(String m) { super(m); }
    }

    // Ten seconds a program, except under the VS Code debugger, where a breakpoint can be held as long as you like.
    private static final int TIMEOUT_SECONDS = java.lang.management.ManagementFactory.getRuntimeMXBean()
            .getInputArguments().stream().anyMatch(a -> a.contains("jdwp")) ? 24 * 60 * 60 : 10;
    private static final int LIMIT = 1_000_000;

    /** True when the source file still carries the stub marker. */
    public static boolean isStub(String className) {
        Path p = Path.of("src", className + ".java");
        try {
            return !Files.exists(p) || Files.readString(p).contains("NOT STARTED YET");
        } catch (Exception e) {
            return true;
        }
    }

    /** Run className.main, feeding it `stdin`, and return everything it printed. */
    public static String run(String className, String stdin) throws NotRunnable {
        final Class<?> c;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new NotRunnable("there is no compiled class called " + className);
        }
        final Method main;
        try {
            main = c.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            throw new NotRunnable(className + " has no main method");
        }

        // Added for the crawler: a program that prints inside a loop that never ends used to fill the heap
        // (OutOfMemoryError), and one that outlived its 10 seconds kept printing into the report.
        // See Sink and ROUTER at the bottom of this file.
        Sink buf = new Sink();
        PrintStream oldErr = System.err;
        InputStream oldIn = System.in;
        AtomicReference<Throwable> thrown = new AtomicReference<>();

        Thread t = new Thread(() -> {
            try {
                main.invoke(null, (Object) new String[0]);
            } catch (InvocationTargetException e) {
                thrown.set(e.getCause());
            } catch (Throwable e) {
                thrown.set(e);
            }
        });

        try {
            System.setIn(new ByteArrayInputStream(stdin.getBytes(StandardCharsets.UTF_8)));
            if (System.out != ROUTER) System.setOut(ROUTER);
            SINKS.put(t, buf);
            System.setErr(new PrintStream(new ByteArrayOutputStream(), true, StandardCharsets.UTF_8));
            t.setDaemon(true);
            t.start();
            t.join(TIMEOUT_SECONDS * 1000L);
        } catch (Exception e) {
            throw new NotRunnable("could not start " + className + ": " + e);
        } finally {
            buf.over = true;
            System.setErr(oldErr);
            System.setIn(oldIn);
        }

        if (buf.flooded) {
            throw new NotRunnable(className + " printed more than a million characters. Look for a loop that never ends.");
        }
        if (t.isAlive()) {
            throw new NotRunnable(className + " was still running after " + TIMEOUT_SECONDS
                    + " seconds. Look for a loop that never ends, or a Scanner waiting for "
                    + "input the test did not give it.");
        }
        Throwable err = thrown.get();
        if (err instanceof java.util.NoSuchElementException) {
            throw new NotRunnable(className + " asked for more input than the test provided. "
                    + "Count your Scanner calls against what the spec says arrives.");
        }
        if (err != null) {
            throw new NotRunnable(className + " crashed: " + err.getClass().getSimpleName()
                    + (err.getMessage() == null ? "" : ": " + err.getMessage()));
        }
        return buf.toString(StandardCharsets.UTF_8);
    }

    /**
     * Pull the value that follows a label, so a program may print whatever
     * prompts it likes around the lines the spec requires.
     * `line(out, "Words:")` finds "Words: 4" and returns "4".
     *
     * The label is found anywhere on the line, not only at the start, because a
     * prompt written with print rather than println leaves the cursor on the
     * same line: `Miles: Truncated: 8 km` has to work.
     */
    public static String line(String output, String label) {
        Matcher m = labelled(label).matcher(output);
        if (!m.find()) return null;
        return m.group(1);
    }

    /** Every value printed after `label`, in order, for programs that repeat one. */
    public static List<String> lines(String output, String label) {
        List<String> found = new ArrayList<>();
        Matcher m = labelled(label).matcher(output);
        while (m.find()) found.add(m.group(1));
        return found;
    }

    private static Pattern labelled(String label) {
        return Pattern.compile(Pattern.quote(label) + "[ \\t]*(.*?)[ \\t]*$", Pattern.MULTILINE);
    }

    // ------------------------------------------------------------------ added for the crawler

    /** What one run printed. Refuses anything past LIMIT, and everything once the run is over. */
    private static class Sink extends ByteArrayOutputStream {
        volatile boolean over = false;
        volatile boolean flooded = false;

        @Override public synchronized void write(int b) { check(1); super.write(b); }
        @Override public synchronized void write(byte[] b, int off, int len) { check(len); super.write(b, off, len); }

        // A RuntimeException, because PrintStream swallows IOException. It ends the runaway thread.
        private void check(int more) {
            if (over) throw new IllegalStateException("the run is over");
            if (count + more > LIMIT) { flooded = true; throw new IllegalStateException("too much output"); }
        }
    }

    private static final PrintStream REAL_OUT = System.out;
    private static final java.util.Map<Thread, Sink> SINKS = new java.util.concurrent.ConcurrentHashMap<>();

    /** System.out for the whole check. A thread that is running a student program writes to its own Sink, for ever. Everyone else writes to the real screen. */
    private static final PrintStream ROUTER = new PrintStream(new java.io.OutputStream() {
        @Override public void write(int b) {
            Sink sink = SINKS.get(Thread.currentThread());
            if (sink != null) sink.write(b); else REAL_OUT.write(b);
        }
        @Override public void write(byte[] b, int off, int len) {
            Sink sink = SINKS.get(Thread.currentThread());
            if (sink != null) sink.write(b, off, len); else REAL_OUT.write(b, off, len);
        }
        @Override public void flush() { REAL_OUT.flush(); }
    }, true, StandardCharsets.UTF_8);
}
