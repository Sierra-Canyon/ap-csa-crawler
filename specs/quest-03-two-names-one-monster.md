# Quest 3 · Two Names One Monster

**Fri 25 September, in class. About 15 minutes. 3 points.**
File: `src/Quest03TwoNamesOneMonster.java`

A variable of type `Monster` does not hold a monster. It holds a reference to one. Two variables
can refer to the same monster, and then a hit through one name shows through the other.

## Input

Four lines: the monster's name (one word), its health, the first hit, the second hit. The stub already reads them.

## Output

For the first case in the table:

```
First: 6
Alias: 6
Copy: 9
First after: 6
Alias after: 5
Copy after: 5
```

- Make a monster called `first`. Its attack, row and column can be anything.
- Make `alias` with `Monster alias = first;`. Do not use `new` on that line.
- Make `copy` with `new` and the same values as `first`.
- Call `first.takeDamage` with the first hit. Print the health of all three: **First**, **Alias**, **Copy**.
- Then `alias = copy;` and call `alias.takeDamage` with the second hit. Print all three again: **First after**, **Alias after**, **Copy after**.

## The cases the tests use

| name | health | first hit | second hit | First | Alias | Copy | First after | Alias after | Copy after | points |
|---|---|---|---|---|---|---|---|---|---|---|
| Grub | 9 | 3 | 4 | 6 | 6 | 9 | 6 | 5 | 5 | 1 |
| Ogre | 5 | 7 | 1 | 0 | 0 | 5 | 0 | 4 | 4 | 1 |
| Bat | 4 | 0 | 4 | 4 | 4 | 4 | 4 | 0 | 0 | 1 |

## Where people lose points

- Every number you print comes from a `getHealth()` call. Six numbers worked out with arithmetic pass the checker, and I read the code as well.
- `Monster alias = new Monster(...)` makes a second monster. Then Alias stays at full health and the Alias line is wrong.
- In the second row the first hit is bigger than the health. `takeDamage` stops at 0, so you do not have to.

## For the log

1. How many Monster objects exist when your program ends, and how many Monster variables?
2. After `alias = copy;` which names refer to the monster that took the first hit?
3. Why did Copy change in the second half when no line of yours says `copy.takeDamage`?

<div class="pagebreak"></div>

# Quest 3 challenge · Change the Leader

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest03Challenge.java`

The party has a `leader` and a `second`. Swap which hero each variable refers to. Then the new leader takes 3 damage.

## Input

Six lines: the first hero's name (one word), health and attack, then the same for the second hero. The first hero starts as `leader`.

## Output

For the first case in the table:

```
Leader: Mote (4/7 health, attack 3)
Second: Rook (10/10 health, attack 4)
```

- Construct exactly two heroes. The checker counts the times `new Hero(` appears in your file.
- After the swap, call `leader.takeDamage(3)`.
- **Leader** is `leader.describe()`. **Second** is `second.describe()`.

## The cases the tests use

| hero | health | attack | hero | health | attack | Leader | Second |
|---|---|---|---|---|---|---|---|
| Rook | 10 | 4 | Mote | 7 | 3 | Mote (4/7 health, attack 3) | Rook (10/10 health, attack 4) |
| Vex | 2 | 5 | Ash | 3 | 1 | Ash (0/3 health, attack 1) | Vex (2/2 health, attack 5) |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. Two variables cannot trade in one step. The first assignment overwrites a reference you still need.
2. A third variable can hold on to one hero while the other two lines run.
3. No hero is copied or changed by the swap. Draw the two objects and move the arrows.
