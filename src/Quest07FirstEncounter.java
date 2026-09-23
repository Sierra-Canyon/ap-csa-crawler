import java.util.Scanner;

/*
 * Quest 7: First Encounter.   The printed sheet has the rules and the cases.
 * NOT STARTED YET   (delete this line when you begin; the checker skips this file while it is here)
 */
public class Quest07FirstEncounter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Hero name: ");
        String heroName = in.nextLine();
        System.out.print("Hero attack: ");
        int attack = in.nextInt();
        in.nextLine();   // eats the end of the attack line, so the next nextLine gets the name
        System.out.print("Monster name: ");
        String monsterName = in.nextLine();
        System.out.print("Monster health: ");
        int health = in.nextInt();
        System.out.print("Roll: ");
        int roll = in.nextInt();

        // Yours starts here. The input has been read into the variables above.
    }
}
