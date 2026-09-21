# Quest 16 · Line of Fire

**Thu 5 November, in class. About 20 minutes. 9 points.**
File: `src/Quest16LineOfFire.java`

An arrow leaves the hero and flies right along the row, one square at a time, until something
stops it or it runs out of reach. This loop has two reasons to stop, and it has to stop at the
first thing it meets.

## Input

Three lines: one row of a level (no spaces), the hero's column, and how many squares the arrow can fly.

## Output

For the first case in the table:

```
Shot: hits
Stopped at: 4
```

- The arrow looks at the square to the right of the hero first, then the next, for as many squares as its reach.
- A wall `#`, a person `N` and a door `+` stop it: **Shot** is `blocked`.
- A monster `M` and a boss `B` stop it: **Shot** is `hits`.
- If it runs out of reach first, **Shot** is `falls short`.
- **Stopped at** is the column of whatever stopped it, or -1 when it fell short.
- Every row ends in a wall or a door, so the arrow cannot leave the String.
- No `break`. Both reasons to stop belong in the loop's condition. The checker looks.

## The cases the tests use

| row | hero col | reach | Shot | Stopped at | points |
|---|---|---|---|---|---|
| `#@..M..#` | 1 | 6 | hits | 4 | 1 |
| `#@..M..#` | 1 | 2 | falls short | -1 | 1 |
| `#@.#M..#` | 1 | 6 | blocked | 3 | 1 |
| `#@.NM#` | 1 | 6 | blocked | 3 | 1 |
| `#@...+` | 1 | 6 | blocked | 5 | 1 |
| `#@M.M#` | 1 | 3 | hits | 2 | 1 |
| `#.@..B.#` | 2 | 3 | hits | 5 | 1 |
| `#@.....#` | 1 | 3 | falls short | -1 | 1 |
| written without `break` |  |  |  |  | 1 |

## Where people lose points

- A loop that keeps going after the first hit reports the second monster in the sixth row. The loop condition has to know that something has already stopped the arrow.
- The seventh row puts the boss exactly at full reach. `<` and `<=` differ there.
- A `for` loop that always runs to the full reach has to be told to ignore everything after the first stop. A condition with two parts stops at the right moment.

## For the log

1. What are the two reasons your loop can stop, and where in the code is each one?
2. In the sixth row, what stops your loop from reaching the second monster?
3. Why did you choose `while` or `for` for this loop?

<div class="pagebreak"></div>

# Quest 16 challenge · Either Way

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest16Challenge.java`

The hero can face left or right. One loop handles both.

## Input

Four lines: one row of a level, the hero's column, the way the hero faces (`a` for left, `d` for right), and the arrow's reach. Every row starts and ends with a wall.

## Output

For the first case in the table:

```
Shot: hits
Stopped at: 1
```

- Everything else is Quest 16.
- Write one loop. The checker counts the times `while` and `for` appear in your file and expects one.

## The cases the tests use

| row | hero col | facing | reach | Shot | Stopped at |
|---|---|---|---|---|---|
| `#M..@..#` | 4 | a | 6 | hits | 1 |
| `#M.#@..#` | 4 | a | 6 | blocked | 3 |
| `#..M@.M#` | 4 | d | 6 | hits | 6 |
| `#M...@.#` | 5 | a | 3 | falls short | -1 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. Only one number is different between the two directions.
2. Find the place where your Quest 16 loop works out which column to look at next.
