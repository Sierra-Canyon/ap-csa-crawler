# Quest 11 · Battle Loop

**Mon 26 October, in class. About 15 minutes. 5 points.**
File: `src/Quest11BattleLoop.java`

Quest 4 was one round. A fight is rounds until somebody falls, and nobody knows in advance how
many that is.

## Input

Four whole numbers, each on its own line: Rook's health, Rook's attack, the Grub's health, the Grub's attack. The stub already reads them.

## Output

For the first case in the table:

```
Rounds: 3
Winner: Rook
Rook now: 4
Grub now: 0
```

- Make a `Hero` called Rook and a `Monster` called Grub.
- In each round Rook hits first, for `Combat.damage` with his attack and a defense of 0. If the Grub is still alive it hits back the same way.
- The rounds go on while both are alive.
- **Rounds** is how many rounds were fought. **Winner** is the name of the one still alive.

## The cases the tests use

| Rook health | Rook attack | Grub health | Grub attack | Rounds | Winner | Rook now | Grub now | points |
|---|---|---|---|---|---|---|---|---|
| 10 | 4 | 9 | 3 | 3 | Rook | 4 | 0 | 1 |
| 5 | 1 | 20 | 3 | 2 | Grub | 0 | 18 | 1 |
| 10 | 4 | 4 | 9 | 1 | Rook | 10 | 0 | 1 |
| 3 | 2 | 5 | 3 | 1 | Grub | 0 | 3 | 1 |
| 10 | 0 | 3 | 0 | 3 | Rook | 8 | 0 | 1 |

## Where people lose points

- A Grub at 0 health that still hits back. The first, third and fifth rows catch it. In the third, Rook would finish on 1.
- `while (rook.isAlive() || grub.isAlive())` never ends when Rook wins. The checker stops the program and says so.
- Rounds starts at 0 and goes up once inside the loop. Starting it at 1 is one too many in every row.

## For the log

1. What has to be true for your loop to run one more round?
2. Trace the first row by hand. What are the two healths at the end of each round?
3. In the last row both attacks are 0. Why does the fight still end?

<div class="pagebreak"></div>

# Quest 11 challenge · Drink at Three

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest11Challenge.java`

Rook carries potions now. When a round starts with Rook on 3 health or less and a potion left, he drinks it and does not attack that round. The Grub still hits back.

## Input

Five whole numbers, each on its own line: Rook's health, Rook's attack, the Grub's health, the Grub's attack, and the potions Rook carries.

## Output

For the first case in the table:

```
Rounds: 6
Winner: Rook
Potions left: 0
Rook now: 3
```

- A potion is `heal(5)`.
- Everything else is Quest 11.

## The cases the tests use

| Rook health | Rook attack | Grub health | Grub attack | potions | Rounds | Winner | Potions left | Rook now |
|---|---|---|---|---|---|---|---|---|
| 8 | 3 | 12 | 3 | 2 | 6 | Rook | 0 | 3 |
| 4 | 1 | 30 | 3 | 1 | 3 | Grub | 0 | 0 |
| 10 | 5 | 5 | 1 | 3 | 1 | Rook | 3 | 10 |
| 9 | 1 | 30 | 3 | 1 | 5 | Grub | 0 | 0 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. A round can now go one of two ways. Decide which before anything else happens in the round.
2. Two things have to be true for Rook to drink. One `if` can test both.
3. `heal` never goes past the health the hero started with. In the second row that is 4.
