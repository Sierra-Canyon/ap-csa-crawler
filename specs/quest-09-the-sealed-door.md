# Quest 9 · The Sealed Door

**Mon 12 October, in class. About 15 minutes. 6 points.**
File: `src/Quest09TheSealedDoor.java`

The door out of a level is sealed while the boss lives. You write that condition twice, in two
forms that De Morgan's laws say are the same, and print both. Then the Sage, who wants 3 gold,
chooses what to say with an `if` inside an `if`.

## Input

Three whole numbers, each on its own line: the boss's health (-1 means this level has no boss), the quest's state (0 not started, 1 started, 3 done), and the gold the party has.

## Output

For the first case in the table:

```
Sealed: true
Sealed again: true
Sage: Bring me 3 gold.
```

- The door is sealed when there is a boss and its health is not 0.
- **Sealed** is that condition written with `&&`.
- **Sealed again** is the same condition written as `!( ... || ... )`. The checker looks for `!(` in your file.
- In state 0 the **Sage** says `Bring me 3 gold.`
- In state 1 the Sage says `You found it. Take this shield.` when the party has 3 gold or more. Otherwise the Sage says `Still looking? You have 2 of 3.`, with the party's real gold in place of the 2.
- In state 3 the Sage says `Thank you again.`

## The cases the tests use

| boss health | quest state | gold | Sealed | Sealed again | Sage | points |
|---|---|---|---|---|---|---|
| 18 | 0 | 0 | true | true | Bring me 3 gold. | 1 |
| 0 | 1 | 3 | false | false | You found it. Take this shield. | 1 |
| -1 | 1 | 2 | false | false | `Still looking? You have 2 of 3.` | 1 |
| 1 | 3 | 5 | true | true | Thank you again. | 1 |
| -1 | 0 | 9 | false | false | Bring me 3 gold. | 1 |
| Sealed again is written with `!(` |  |  |  |  |  | 1 |

## Where people lose points

- The opposite of `a || b` is `!a && !b`. Flipping the operator and leaving the two pieces alone is the usual slip.
- In the last row the party has 9 gold and the quest has not started. The gold test belongs inside the state 1 branch, where it cannot fire early.
- The Sage's lines are compared exactly, period included.

## For the log

1. Write your two Sealed conditions next to each other. Which De Morgan law turns one into the other?
2. Which rows of the table would a program fail if it tested the gold before the state?
3. How many paths are there through your Sage code, and which row takes each one?

<div class="pagebreak"></div>

# Quest 9 challenge · Stuck

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest09Challenge.java`

The party can leave a level when the door is not sealed, and also either every monster is gone or they hold a key. Print whether they can leave, and print whether they are stuck. There is no `!` anywhere in this program.

## Input

Three whole numbers, each on its own line: the boss's health (-1 means no boss), the monsters left, and the keys the party holds.

## Output

For the first case in the table:

```
Leave: true
Stuck: false
```

- The door is sealed when the boss's health is above 0.
- **Leave** is one boolean expression built from the three numbers.
- **Stuck** is always the opposite of Leave, and it is also built from the three numbers. It does not mention Leave.
- No `!` in the file. `!=` is fine. The checker counts, and it also looks for `== false`.

## The cases the tests use

| boss health | monsters | keys | Leave | Stuck |
|---|---|---|---|---|
| -1 | 0 | 0 | true | false |
| 0 | 3 | 1 | true | false |
| 5 | 0 | 1 | false | true |
| -1 | 2 | 0 | false | true |
| 0 | 0 | 0 | true | false |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. De Morgan works on the outside first: the opposite of `a && b` is `!a || !b`. Then do it again to whichever piece is still a compound.
2. The opposite of `>=` is `<`. It is not `<=`.
3. Fill in a truth table for three rows of the case table before you trust the line.
