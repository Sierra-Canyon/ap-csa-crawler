# Quest 13 · Battle Report

**Fri 30 October, in class. About 15 minutes. 4 points.**
File: `src/Quest13BattleReport.java`

After a fight the game could tell you how it went. There is no list to keep the hits in yet, so
each number is read, used and forgotten inside one loop.

## Input

A whole number, which is how many swings there were (at least 1). Then that many whole numbers, each on its own line: the damage of each swing. A miss is 0.

## Output

For the first case in the table:

```
Hits: 3
Total: 13
Biggest: 6
Average: 2.6
```

- **Hits** counts the swings that did more than 0.
- **Total** is all the damage added up.
- **Biggest** is the largest single swing. `Math.max` is not on the Quick Reference.
- **Average** is the total divided by the number of swings, misses included, as a `double`. Anything within 0.01 passes.

## The cases the tests use

| swings | damage of each | Hits | Total | Biggest | Average | points |
|---|---|---|---|---|---|---|
| 5 | 3 0 4 6 0 | 3 | 13 | 6 | 2.6 | 1 |
| 1 | 0 | 0 | 0 | 0 | 0.0 | 1 |
| 4 | 2 2 2 3 | 4 | 9 | 3 | 2.25 | 1 |
| 3 | 1 9 1 | 3 | 11 | 9 | 3.67 | 1 |

## Where people lose points

- `total / swings` with two `int`s is 2 in the first row, not 2.6. One side has to be a `double` before the division happens.
- `(double) (total / swings)` casts too late. The whole number division has already thrown the .6 away.
- A variable declared inside the loop is new on every pass and gone after the last one.

## For the log

1. What value did Biggest start at, and why is that value safe for this input?
2. Where exactly did you put the cast, and what does Java do differently because of it?
3. Which of your variables are declared before the loop, and why do they have to be?

<div class="pagebreak"></div>

# Quest 13 challenge · The Weakest Hit

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest13Challenge.java`

The same input. This time report the misses, and the smallest swing that was not a miss.

## Input

The same as Quest 13.

## Output

For the first case in the table:

```
Misses: 2
Smallest hit: 3
```

- **Misses** counts the swings of 0.
- **Smallest hit** is the smallest swing above 0. When every swing missed it is the word `none`.

## The cases the tests use

| swings | damage of each | Misses | Smallest hit |
|---|---|---|---|
| 5 | 3 0 4 6 0 | 2 | 3 |
| 1 | 0 | 1 | none |
| 4 | 2 2 2 3 | 0 | 2 |
| 3 | 0 0 7 | 2 | 7 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. Starting the smallest at 0 cannot work here. Nothing above 0 is smaller than 0.
2. Your program needs a way to tell "no hit yet" apart from a real hit. A `boolean` can do it. So can a starting value that no real hit could have.
