# Quest 6 · The Wall Bug

**Thu 1 October, in class. About 15 minutes. 4 points.**
File: `src/Quest06TheWallBug.java`

You saw the bug in the demo: the hero walked through a wall because the game asked
`tile == "#"`. Today you print what `==` says and what `equals` says, side by side.

## Input

Four lines: one row of a level (no spaces), a column number, and two names (one word each). The stub already reads them.

## Output

For the first case in the table:

```
Tile: [#]
Wall by ==: false
Wall by equals: true
Compare: 5
Row still: #.@M$#
```

- **Tile** is the one character String at that column, from `substring`, inside square brackets.
- **Wall by ==** is the value of `tile == "#"`.
- **Wall by equals** is the value of `tile.equals("#")`.
- **Compare** is the first name's `compareTo` with the second name.
- **Row still** is the row, printed after all of that.

## The cases the tests use

| row | column | name | name | Tile | Wall by == | Wall by equals | Compare | Row still | points |
|---|---|---|---|---|---|---|---|---|---|
| `#.@M$#` | 0 | Rook | Mote | `[#]` | false | true | 5 | `#.@M$#` | 1 |
| `#.@M$#` | 3 | Mote | Rook | `[M]` | false | false | -5 | `#.@M$#` | 1 |
| `##..+` | 4 | Rook | Rook | `[+]` | false | false | 0 | `##..+` | 1 |
| `#` | 0 | Rook | Rookie | `[#]` | false | true | -2 | `#` | 1 |

## Where people lose points

- Wall by == is `false` in every row of the table, including the rows where the tile is a wall. That is the bug. Printing `true` there means you compared two literals and never used your tile.
- `substring(column, column)` is the empty String. One character needs `column + 1` as the end.
- `"Wall by ==: " + tile == "#"` prints `false` and no label. `+` happens before `==`, so Java joins the text first and then compares the whole line with `"#"`. Put the comparison in parentheses.

## For the log

1. What does `==` compare when both sides are Strings?
2. Compare was 5 for Rook and Mote. Where does the 5 come from?
3. Which line of your program could have changed the row, and did it?

<div class="pagebreak"></div>

# Quest 6 challenge · Clear the Square

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest06Challenge.java`

A monster has been defeated and its square becomes floor. A String cannot be changed, so make a new row with a `.` at that column.

## Input

Two lines: one row of a level (no spaces) and a column number.

## Output

For the first case in the table:

```
Before: #.@M$#
After: #.@.$#
Before again: #.@M$#
```

- **Before** is the row as it arrived.
- **After** is a new String: the same row with `.` at that column.
- **Before again** is the first variable, printed last. It has not changed.

## The cases the tests use

| row | column | Before | After | Before again |
|---|---|---|---|---|
| `#.@M$#` | 3 | `#.@M$#` | `#.@.$#` | `#.@M$#` |
| `M..#` | 0 | `M..#` | `...#` | `M..#` |
| `#..M` | 3 | `#..M` | `#...` | `#..M` |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. No String method changes a String. Each one returns a new String.
2. The new row is built from pieces of the old row and one new tile. Count the pieces.
3. Try your line by hand on the first square and on the last square. One of the pieces is empty there, and that is fine.
