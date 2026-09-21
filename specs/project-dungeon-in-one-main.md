# Project · Dungeon in One Main

**Built in class across three meetings, 19 November to 24 November. Demos on Monday 30 November. 50 points.**
File: `src/DungeonInOneMain.java`

One program, all in `main`. No methods of your own and no arrays: those are Units 3 and 4.
It is a whole small game: a map, a hero who walks, gold, monsters, and a door that stays sealed
until the monsters are gone. The Thanksgiving break falls between the last build day and the
demos. Working over the break is optional and never required.

## Input

- The width of the map.
- The map as one String with no spaces, the way Quests 15 and 17 had it. Its tiles are `#` wall, `.` floor, `@` the hero, `M` a monster, `$` gold and `+` the door. There is exactly one `@`, and the edge of every map is wall or door.
- The hero's health.
- Then commands, one word per line, ending with the word `quit`.

## The rules

- `w`, `a`, `s` and `d` try to move the hero one square up, left, down or right. Any other word does nothing.
- Into a wall `#`: nothing happens.
- Onto floor `.`: the hero moves. That is one step.
- Onto gold `$`: the hero moves, that is one step, and the party has 1 more gold.
- Into a monster `M`: the monster is defeated and its square becomes floor. The hero does not move and loses 2 health. Health never goes below 0. At 0 health the game is **lost**.
- Into the door `+` while any `M` is left on the map: nothing happens. The door is sealed.
- Into the door `+` with no `M` left: the hero moves onto it, that is one step, and the game is **won**.
- When the hero moves, the square the hero left becomes floor.
- Once the game is won or lost, no later command changes anything.
- If `quit` arrives first, the result is **quit**.

## Output

After the last command, these lines. For the fifth case in the table:

```
Result: won
Gold: 1
Health left: 8
Steps: 4
Monsters left: 0
Hero at: 2,4
Final map: ######
Final map: #....#
Final map: #.$.@#
Final map: ######
```

- **Hero at** is the hero's row, a comma, and the column, counting from 0.
- **Final map** is the map as it is at the end, one line per row, each starting with `Final map:`.
- You may print anything else you like along the way, such as the map after every move, so that you can play your own game by running it and typing. The checker reads the **last** line that carries each label, so print these lines last. `Final map:` is the exception: every line with that label counts, so draw the map along the way under some other label.

## The maps the tests use

```
Map A, 6 wide      Map B, 7 wide      Map C, 10 wide
######             #######            ##########
#@.$M#             #@M.M+#            #@.$.#..M#
#.$.+#             #######            #.##.#.#.#
######                                #..M...#+#
                                      ##########
```

## The cases the tests use

| case | map | health | commands | Result | Gold | Health left | Steps | Monsters left | Hero at | points |
|---|---|---|---|---|---|---|---|---|---|---|
| walking and walls | A | 10 | `a w s s w quit` | quit | 0 | 10 | 2 | 1 | 1,1 | 5 |
| picking up gold | A | 10 | `d d s a quit` | quit | 2 | 10 | 4 | 1 | 2,2 | 5 |
| a fight, then walking on | A | 10 | `d d d d quit` | quit | 1 | 8 | 3 | 0 | 1,4 | 5 |
| the door is sealed while a monster lives | A | 10 | `s d d d quit` | quit | 1 | 10 | 3 | 1 | 2,3 | 5 |
| winning, and the commands after it | A | 10 | `d d d d s a a quit` | won | 1 | 8 | 4 | 0 | 2,4 | 5 |
| losing, and the commands after it | B | 3 | `d d d d d d quit` | lost | 0 | 0 | 2 | 0 | 1,3 | 5 |
| a command the game does not know | A | 10 | `x d jump d quit` | quit | 1 | 10 | 2 | 1 | 1,3 | 5 |
| a bigger level, start to finish | C | 10 | `d d d s s a d d d w w d d d s s quit` | won | 1 | 6 | 13 | 0 | 3,8 | 5 |

The checker compares the final map in every case as well.

## Graded on

| | Points | Checked by |
|---|---|---|
| The eight cases above, 5 points each | 40 | tests |
| The demo, and answering a question about your own code | 10 | me |

## Where people lose points

- Building a new row with `substring` changes nothing until the answer is assigned back to `map`. Quest 6's challenge was this line.
- Two `@` on the final map. The square the hero left has to become floor.
- The target of `w` is the hero's index minus the width, and `s` is plus the width. Quest 15 found a row and a column from an index. This is that idea the other way around.
- The fifth and sixth cases send more commands after the game is over. The loop has to know the game has ended.
- `command == "quit"`. Quest 6.

## For the log, on demo day

1. Which part of your `main` would you most want to turn into a method of its own, and what would you call it?
2. The monsters in this game fall in one hit. What would your program need before each monster could have its own health?
3. Which quest did you reuse the most from, and what did you have to change?

<div class="pagebreak"></div>

# Project extra · The f Command

**Extra. Not graded. The checker marks it `extra`, and only looks once your file prints `Arrows left:`.**
File: the same one.

The hero carries 3 arrows. The command `f` fires one to the right along the hero's row. This is
Quest 16 inside your game.

- An arrow flies up to 3 squares.
- A wall `#` or the door `+` stops it.
- The first monster `M` it meets is defeated, and its square becomes floor. The hero loses no health.
- Gold does not stop it.
- Firing uses up an arrow whether or not it hits. With no arrows left, `f` does nothing.
- Firing is not a step.
- One more line of output at the end: `Arrows left:`.

```
Map D, 9 wide      Map E, 8 wide
#########          ########
#@..M.M##          #@...M.#
#.#M$...#          ########
#########
```

| case | map | commands | Arrows left | Monsters left | the hero's row at the end |
|---|---|---|---|---|---|
| an arrow hits a monster two squares away | D | `d f quit` | 2 | 2 | `#.@...M##` |
| the first monster in line, not the second | D | `d d f d d f quit` | 1 | 1 | `#....@.##` |
| a monster exactly at full reach | D | `f quit` | 2 | 2 | `#@....M##` |
| out of reach | E | `f quit` | 2 | 1 | `#@...M.#` |
| a wall stops it | D | `s f quit` | 2 | 3 | `#@#M$...#` |
| only three arrows | D | `f f f d f quit` | 0 | 2 | `#.@...M##` |

The checker compares the whole final map.

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. The arrow's squares are the hero's index plus 1, plus 2 and plus 3. They are all in the same row, because every row ends in a wall.
2. The arrow's loop has two reasons to stop, and neither of them is `break`. Quest 16.
3. Defeating a monster with an arrow changes the map the same way defeating one with a sword does. You already have that line.
