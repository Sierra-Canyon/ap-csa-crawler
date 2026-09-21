# Quest 15 · Read the Map

**Wed 4 November, in class. About 15 minutes. 4 points.**
File: `src/Quest15ReadTheMap.java`

There are no arrays until Unit 4, so today a whole level is one long String: every row, one
after another, with nothing between them. The width tells you where each row ends. The first
case is this map, which is 6 wide:

```
######
#@.$M#
#.$.M#
######
```

## Input

Two lines: the width of the map, and the map as one String with no spaces (`in.next()` reads it).

## Output

For the first case in the table:

```
Gold: 2
Monsters: 2
Hero row: 1
Hero col: 1
```

- **Gold** counts the `$` tiles. **Monsters** counts the `M` tiles.
- Walk the String one character at a time with `substring(i, i + 1)` and compare with `equals`. There is no `charAt` in this course.
- **Hero row** and **Hero col** say where the `@` is. Rows and columns count from 0. Both come from the `@`'s index and the width, with `/` and `%`.

## The cases the tests use

| width | map | Gold | Monsters | Hero row | Hero col | points |
|---|---|---|---|---|---|---|
| 6 | `###### / #@.$M# / #.$.M# / ######` | 2 | 2 | 1 | 1 | 1 |
| 5 | `##### / #..M# / #$.@# / #####` | 1 | 1 | 2 | 3 | 1 |
| 8 | `######## / #M$M$M@# / ########` | 2 | 3 | 1 | 6 | 1 |
| 20 | given/levels/level3.txt, its rows joined | 4 | 5 | 1 | 1 | 1 |

## Where people lose points

- `i <= map.length()` runs one step too far, and `substring` throws a StringIndexOutOfBoundsException. The last index is `length() - 1`.
- `tile == "$"` counts nothing. Quest 6 again.
- Mixing up `/` and `%`. In the third case the `@` is at index 14 of a map 8 wide, which is row 1 and column 6.

## For the log

1. In the first case the `@` is at index 7. How do 7 and the width of 6 give row 1 and column 1?
2. How many times does your loop body run for the first case?
3. `indexOf` would also find the `@`. What can your loop do that `indexOf` cannot?

<div class="pagebreak"></div>

# Quest 15 challenge · Nearest in the Row

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest15Challenge.java`

How far is the nearest monster in the hero's own row? Look both ways. Walls do not matter for this one.

## Input

The same as Quest 15.

## Output

For the first case in the table:

```
Nearest: 3
```

- **Nearest** is how many columns apart the `@` and the closest `M` in the same row are. A monster right beside the hero is 1.
- With no `M` in that row it is the word `none`.

## The cases the tests use

| width | map | Nearest |
|---|---|---|
| 6 | `###### / #@.$M# / #.$.M# / ######` | 3 |
| 5 | `##### / #..M# / #$.@# / #####` | none |
| 8 | `######## / #M$M$M@# / ########` | 1 |
| 8 | `######## / #M.@..M# / ########` | 2 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. Find the hero first. The hero's row number tells you the index where that row starts, and the width tells you where it ends.
2. A distance is never negative. `Math.abs` is on the Quick Reference.
3. Decide what your nearest variable holds before any monster has been found, and how you will know that at the end.
