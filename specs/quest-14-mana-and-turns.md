# Quest 14 · Mana and Turns

**Mon 2 November, in class. About 20 minutes. 7 points.**
File: `src/Quest14ManaAndTurns.java`

Mana comes back slowly: one point on every fourth turn. A spark costs 2. The player types
commands until they type `quit`, and nobody knows how many that will be, so the loop watches
for that one word.

## Input

A whole number, the mana the party starts with (0 to 6). Then one word per line, each `wait` or `spark`, and last of all the word `quit`.

## Output

For the first case in the table:

```
Turns: 3
Casts: 3
Refused: 1
Mana left: 0
```

- `wait` uses up a turn.
- `spark` with 2 mana or more costs 2 mana, counts as a cast, and uses up a turn.
- `spark` with less than 2 mana is refused. It costs nothing and does not use up a turn.
- After every turn that was used up: if the number of turns so far is a multiple of 4, the party gains 1 mana. Mana never goes above 6.
- `quit` ends the input. It is not a turn.

## The cases the tests use

| mana | commands | Turns | Casts | Refused | Mana left | points |
|---|---|---|---|---|---|---|
| 6 | spark spark spark spark | 3 | 3 | 1 | 0 | 1 |
| 0 | wait wait wait wait spark | 4 | 0 | 1 | 1 | 1 |
| 1 | wait wait wait spark wait spark | 5 | 1 | 1 | 0 | 1 |
| 6 | (none) | 0 | 0 | 0 | 6 | 1 |
| 6 | wait, eight times | 8 | 0 | 0 | 6 | 1 |
| 5 | wait wait wait spark | 4 | 1 | 0 | 4 | 1 |
| 6 | wait wait wait spark | 4 | 1 | 0 | 5 | 1 |

## Where people lose points

- `while (command != "quit")` never stops at `quit`. Run it yourself and it keeps asking. The checker reports that it asked for more input than the test provided. That is Quest 6 again: `==` and `!=` on Strings compare references.
- Reading the next command in only one place. The usual shape reads once before the loop and once more at the bottom of it.
- In the third row the refused spark is not a turn. A program that counts it prints Turns: 6.

## For the log

1. Where does your program read a command, and why does it need to do that in more than one place?
2. What does `turns % 4 == 0` ask, in words?
3. In the fifth row the mana gain fires twice and the mana stays at 6. Which part of your code is responsible?

<div class="pagebreak"></div>

# Quest 14 challenge · The Longest Rest

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest14Challenge.java`

Find the longest run of `wait` commands in a row.

## Input

One word per line, each `wait` or `spark`, and last of all the word `quit`. There is no starting mana this time.

## Output

For the first case in the table:

```
Longest rest: 3
```

- A `spark` ends a run, whether or not it could be cast.
- With no `wait` at all the answer is 0.

## The cases the tests use

| commands | Longest rest |
|---|---|
| wait wait spark wait wait wait spark | 3 |
| (none) | 0 |
| spark spark | 0 |
| wait spark wait wait wait wait | 4 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. You have to remember two things, and they change at different moments.
2. Something has to set the first counter back to 0.
3. Look at the last row. If your best is only updated when a run ends, that run never gets counted.
