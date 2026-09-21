# Quest 10 · Boss Phase

**Tue 13 October, in class. About 20 minutes. 8 points.**
File: `src/Quest10BossPhase.java`

The boss fights in three phases, decided by how much of its health is left. Today you write
that rule yourself, in `main`, with whole numbers only.

## Input

Four whole numbers, each on its own line: the boss's health, the boss's full health, the hero's attack, the boss's attack. The stub already reads them.

## Output

For the first case in the table:

```
Phase: 1
Shockwave every: 0
Harder hitter: boss
```

- **Phase** is 1 while the boss has more than two thirds of its full health, 2 while it has more than one third, and 3 otherwise. Exactly two thirds is phase 2.
- **Shockwave every** is 0 in phase 1, 4 in phase 2 and 3 in phase 3.
- **Harder hitter** is `hero`, `boss` or `tie`, by comparing the two attacks, without `Math.max`.
- Do not call `Combat.bossPhase` or `Combat.shockwaveEvery`. The checker looks.

## The cases the tests use

| health | full health | hero attack | boss attack | Phase | Shockwave every | Harder hitter | points |
|---|---|---|---|---|---|---|---|
| 18 | 18 | 4 | 5 | 1 | 0 | boss | 1 |
| 13 | 18 | 4 | 5 | 1 | 0 | boss | 1 |
| 12 | 18 | 5 | 5 | 2 | 4 | tie | 1 |
| 7 | 18 | 6 | 5 | 2 | 4 | hero | 1 |
| 6 | 18 | 4 | 7 | 3 | 3 | boss | 1 |
| 1 | 20 | 4 | 4 | 3 | 3 | tie | 1 |
| 14 | 20 | 9 | 1 | 1 | 0 | hero | 1 |
| written without `Combat.bossPhase` and `Combat.shockwaveEvery` |  |  |  |  |  |  | 1 |

## Where people lose points

- `health / fullHealth > 2 / 3` is `0 > 0` for every row except the first. Both divisions are whole number divisions. The same question can be asked with no division in it at all.
- The third and fifth rows sit exactly on a boundary. `>` and `>=` differ there and nowhere else.
- Harder hitter has three answers. An `if` with one `else` can only give two.

## For the log

1. How did you compare the health to two thirds without dividing?
2. Which two rows of the table would change if you wrote `>=` in place of `>`?
3. How could you use `Combat.bossPhase` to check your own answer without calling it in this file?

<div class="pagebreak"></div>

# Quest 10 challenge · Which Powerup

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest10Challenge.java`

A `*` on the map is a powerup. Which one depends on where it is: the game adds the row and the column and looks at the remainder after dividing by 3.

## Input

Four whole numbers, each on its own line: the row, the column, how many turns the powerup has left, and the hero's damage before any bonus.

## Output

For the first case in the table:

```
Powerup: might
Damage with it: 6
```

- A remainder of 0 is `might`, 1 is `guard` and 2 is `regen`.
- **Damage with it** is the hero's damage plus 2 when the powerup is might and it has at least 1 turn left. Otherwise it is the hero's damage unchanged.
- Do not call `Powerups.nameAt`.

## The cases the tests use

| row | col | turns left | damage | Powerup | Damage with it |
|---|---|---|---|---|---|
| 3 | 12 | 5 | 4 | might | 6 |
| 3 | 12 | 0 | 4 | might | 4 |
| 1 | 3 | 5 | 4 | guard | 4 |
| 2 | 3 | 9 | 1 | regen | 1 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. `%` gives the remainder. Dividing by 3 leaves only three possible remainders.
2. The bonus needs two things to be true at the same time.
