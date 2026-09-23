import java.util.Scanner;

/*
 * Quest 7: First Encounter.   The printed sheet has the rules and the cases.
 * NOT STARTED YET   (delete this line when you begin; the checker skips this file while it is here)
 */
public class Quest07FirstEncounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hero name: ");
        String heroName = scanner.nextLine();
        System.out.print("Hero attack: ");
        int attack = scanner.nextInt();
        scanner.nextLine();   // eats the end of the attack line, so the next nextLine gets the name
        System.out.print("Monster name: ");
        String monsterName = scanner.nextLine();
        System.out.print("Monster health: ");
        int health = scanner.nextInt();
        System.out.print("Roll: ");
        int roll = scanner.nextInt();

        // Yours starts here. The input has been read into the variables above.
    }
}
