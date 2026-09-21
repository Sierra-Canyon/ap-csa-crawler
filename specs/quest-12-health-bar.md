# Quest 12 · Health Bar

**Tue 27 October, in class. About 15 minutes. 5 points.**
File: `src/Quest12HealthBar.java`

The bar at the top of the game is a String built one square at a time. You know how many squares
before the loop starts, so this is a `for` loop.

## Input

Two whole numbers, each on its own line: the health and the full health. The health is never negative and never more than the full health. The full health is at least 1.

## Output

For the first case in the table:

```
Bar: [####......]
Percent: 40
```

- **Bar** is `[`, then one character for each point of full health, then `]`. A point the hero still has is `#`. A point that is gone is `.`.
- Build the bar in a String variable with a `for` loop, and print it once.
- **Percent** is the health as a whole number percentage of the full health, rounded down.

## The cases the tests use

| health | full health | Bar | Percent | points |
|---|---|---|---|---|
| 4 | 10 | `[####......]` | 40 | 1 |
| 0 | 10 | `[..........]` | 0 | 1 |
| 10 | 10 | `[##########]` | 100 | 1 |
| 1 | 3 | `[#..]` | 33 | 1 |
| 7 | 8 | `[#######.]` | 87 | 1 |

## Where people lose points

- `i <= fullHealth` with `i` starting at 0 draws one square too many. Count the characters between the brackets in the fourth row.
- `health / fullHealth * 100` is 0 for every row except the third. The division happens first, and it is a whole number division.
- Two loops, one for `#` and one for `.`, is fine. So is one loop with an `if` inside.

## For the log

1. How many times does your loop body run for 1 of 3, and what is `i` each time?
2. What did you do so that Percent was 87 and not 0 for 7 of 8?
3. What is the first value of `i` for which your loop adds a `.` in the first row?

<div class="pagebreak"></div>

# Quest 12 challenge · Boss Bar

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest12Challenge.java`

The boss's bar has a mark at each third, so the player can see the next phase coming.

## Input

Two whole numbers, each on its own line: the boss's health and its full health. The full health is always a multiple of 3.

## Output

For the first case in the table:

```
Boss: [##|#.|..]
```

- The bar is the Quest 12 bar with a `|` after the square that ends the first third and after the square that ends the second third.
- There is no `|` before the first square and none after the last.

## The cases the tests use

| health | full health | Boss |
|---|---|---|
| 3 | 6 | <code>[##&#124;#.&#124;..]</code> |
| 18 | 18 | <code>[######&#124;######&#124;######]</code> |
| 0 | 3 | <code>[.&#124;.&#124;.]</code> |
| 7 | 9 | <code>[###&#124;###&#124;#..]</code> |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. A mark goes after a square, and only after some of them.
2. With 18 squares the marks follow the 6th and the 12th. What do 6 and 12 have in common that 5 and 7 do not?
