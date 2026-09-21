# Quest 2 · Meet the Party

**Thu 24 September, in class. About 15 minutes. 3 points.**
File: `src/Quest02MeetTheParty.java`

`Hero` and `Monster` are classes in the game. Today you make one object of each with `new`, and
ask each object about itself. The constructors and methods are listed in `given/API.md`.

## Input

Eight lines: the hero's name, health and attack, then the monster's name, health, attack, row and column. Each name is one word. The stub already reads them.

## Output

For the first case in the table:

```
Hero: Rook (10/10 health, attack 4)
Monster: Grub (4 health, attack 2)
Square: 3,5
Boss: null
```

- **Hero** is what the hero's `describe()` returns.
- **Monster** is what the monster's `describe()` returns.
- **Square** is the monster's row, a comma, and its column, from `getRow()` and `getCol()`.
- **Boss** is a third variable, of type `Monster`, that you set to `null` and then print. There is no boss on this level.

## The cases the tests use

| hero | health | attack | monster | health | attack | row | col | Hero | Monster | Square | Boss | points |
|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Rook | 10 | 4 | Grub | 4 | 2 | 3 | 5 | Rook (10/10 health, attack 4) | Grub (4 health, attack 2) | 3,5 | null | 1 |
| Mote | 7 | 3 | Bat | 3 | 3 | 1 | 12 | Mote (7/7 health, attack 3) | Bat (3 health, attack 3) | 1,12 | null | 1 |
| Vex | 1 | 5 | Ogre | 12 | 6 | 0 | 0 | Vex (1/1 health, attack 5) | Ogre (12 health, attack 6) | 0,0 | null | 1 |

## Where people lose points

- `new Hero(name, attack, health)` compiles. Both are `int`, so Java cannot tell they are in the wrong order. The constructor's parameter list in `given/API.md` is the only place the order is written down.
- `"Square: " + monster.getRow() + monster.getCol()` prints `Square: 35` for the first row. Nothing puts a comma there unless you do.
- `Monster boss;` with no `= null` does not compile when you print it. A local variable has no value until you give it one.

## For the log

1. Which argument in your `new Monster(...)` line sets the row, and how did you know?
2. What did Java print for the boss variable, and what does that value mean?
3. What would `boss.describe()` do when you run it, and why?

<div class="pagebreak"></div>

# Quest 2 challenge · Two Ways to Make a Spell

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest02Challenge.java`

`Spell` has two constructors. Make one spell with each, from the same input, and compare them.

## Input

Four lines: the spell's name (one word), its mana cost, its damage up close, its damage far away.

## Output

For the first case in the table:

```
First: zap (2 mana)
First kind: spark
Second kind: bolt
Second at 1: 5
Second at 5: 2
First at 5: 4
```

- The first spell uses the constructor with three parameters. Its kind is `"spark"`.
- The second spell uses the constructor with four parameters.
- **First** is the first spell's `describe()`. The **kind** lines come from `getKind()`. The **at** lines come from `damageAt` with that distance.

## The cases the tests use

| name | cost | near | far | First | First kind | Second kind | Second at 1 | Second at 5 | First at 5 |
|---|---|---|---|---|---|---|---|---|---|
| zap | 2 | 5 | 2 | zap (2 mana) | spark | bolt | 5 | 2 | 4 |
| flare | 9 | 12 | 3 | flare (6 mana) | spark | bolt | 9 | 3 | 4 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. Java picks a constructor by how many arguments you pass and what types they are.
2. Nothing in your program changes 9 into 6. Read the constructors in `given/API.md` to see who does.
3. The second row of the table has a 12 going in and a 9 coming out. That is in the API notes too.
