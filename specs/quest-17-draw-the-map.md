# Quest 17 · Draw the Map

**Tue 10 November, in class. About 15 minutes. 4 points.**
File: `src/Quest17DrawTheMap.java`

The map is still one long String. Drawing it takes a loop over the rows with a loop over the
columns inside it, and the index of each tile comes from both loop variables.

## Input

Two lines: the width of the map, and the map as one String with no spaces. The length of the map is always a multiple of the width.

## Output

For the first case in the table:

```
Size: 4 by 6
Drawn: # # # # # #
Drawn: # @ . $ M #
Drawn: # . $ . M #
Drawn: # # # # # #
```

- **Size** is the number of rows, the word `by`, and the number of columns.
- Each row of the map is printed on a line of its own that starts with `Drawn:`.
- Inside a row the tiles have one space between them, which is why one `substring` per row is not enough.

## The cases the tests use

| width | map | Size | Drawn | points |
|---|---|---|---|---|
| 6 | `###### / #@.$M# / #.$.M# / ######` | 4 by 6 | `# # # # # #`<br>`# @ . $ M #`<br>`# . $ . M #`<br>`# # # # # #` | 1 |
| 5 | `##### / #..M# / #$.@# / #####` | 4 by 5 | `# # # # #`<br>`# . . M #`<br>`# $ . @ #`<br>`# # # # #` | 1 |
| 8 | `######## / #M$M$M@# / ########` | 3 by 8 | `# # # # # # # #`<br>`# M $ M $ M @ #`<br>`# # # # # # # #` | 1 |
| 20 | given/levels/level3.txt, its rows joined | 11 by 20 | all 11 rows, drawn the same way | 1 |

## Where people lose points

- The index of a tile comes from its row, its column and the width. `row + col` and `row * col` both compile.
- Printing `Drawn:` inside the inner loop puts the label in front of every tile.
- `System.out.print` stays on the line. `System.out.println()` with nothing in it ends the line, once per row.

## For the log

1. For the first case, how many times does the inner loop's body run in total?
2. Which of your two loop variables changes fastest, and how does that match the order the tiles print in?
3. What index does your formula give for the hero in the first case, and does it match Quest 15?

<div class="pagebreak"></div>

# Quest 17 challenge · Fog

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest17Challenge.java`

The party carries one torch. Only the squares within 2 steps of the hero can be seen. Everything else is drawn as `?`.

## Input

The same as Quest 17.

## Output

For the first case in the table:

```
Drawn: ###???
Drawn: #@.$??
Drawn: #.$???
Drawn: ?#????
```

- Steps are counted the way `Combat.distance` counts them: across plus down.
- A square exactly 2 steps away can be seen.
- No spaces between the tiles this time.

## The cases the tests use

| width | map | Drawn |
|---|---|---|
| 6 | `###### / #@.$M# / #.$.M# / ######` | `###???`<br>`#@.$??`<br>`#.$???`<br>`?#????` |
| 5 | `##### / #..M# / #$.@# / #####` | `???#?`<br>`??.M#`<br>`?$.@#`<br>`??###` |
| 8 | `######## / #M$M$M@# / ########` | `?????###`<br>`????$M@#`<br>`?????###` |
| 20 | given/levels/level3.txt, its rows joined | all 11 rows. The first three are<br>`###?????????????????`<br>`#@..????????????????`<br>`#.#?????????????????` |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. You need the hero's row and column before any drawing starts. That is a job of its own, and Quest 15 did it.
2. `Combat.distance` takes four numbers: the row and column of one square, then the row and column of the other.
3. Inside the inner loop you know the row and the column of the tile you are about to draw.
