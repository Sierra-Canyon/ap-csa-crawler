import java.util.Scanner;

/*
 * Quest 8: Can I Cast.   The printed sheet has the rules and the cases.
 * NOT STARTED YET   (delete this line when you begin; the checker skips this file while it is here)
 */
public class Quest08CanICast {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Spell: ");
        String name = in.next();
        System.out.print("Mana: ");
        int mana = in.nextInt();
        System.out.print("Distance: ");
        int distance = in.nextInt();

        Spell spell = null;
        if (name.equals("spark")) {
            spell = new Spell("spark", 2, "spark");
        } else if (name.equals("quake")) {
            spell = new Spell("quake", 4, "quake");
        }

        // Yours starts here. spell is null when the name was "none".
    }
}
