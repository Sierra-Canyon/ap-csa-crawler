# Quest 5 · Parse the Command

**Wed 30 September, in class. About 15 minutes. 4 points.**
File: `src/Quest05ParseTheCommand.java`

In the text game a player types `fire d` or `cast spark d`. The first word is the verb and the
rest says what to do it to. The game splits the line with `indexOf` and `substring`, and so do you.

## Input

One line: a command with at least one space in it. Read it with `in.nextLine()`.

## Output

For the first case in the table:

```
Verb: [fire]
Rest: [d]
Space at: 4
Letters: 6
```

- **Verb** is everything before the first space, inside square brackets.
- **Rest** is everything after the first space, inside square brackets. The brackets are there so that a stray space shows.
- **Space at** is the index of the first space.
- **Letters** is the length of the whole command, spaces included.

## The cases the tests use

| command | Verb | Rest | Space at | Letters | points |
|---|---|---|---|---|---|
| fire d | `[fire]` | `[d]` | 4 | 6 | 1 |
| cast spark d | `[cast]` | `[spark d]` | 4 | 12 | 1 |
| lead Mote | `[lead]` | `[Mote]` | 4 | 9 | 1 |
| throw w | `[throw]` | `[w]` | 5 | 7 | 1 |

## Where people lose points

- `substring(0, space)` stops before the space. The second number is the first index you do not want.
- `substring(space)` starts on the space, so Rest prints as `[ d]`.
- `in.next()` reads one word and stops. The rest of the command is still waiting in the Scanner.

## For the log

1. For `throw w`, which two numbers did you pass to substring to get the verb?
2. What does `command.substring(space + 1)` mean when there is only one argument?
3. What would `indexOf` return for a command with no space, and what would your substring call do with that?

<div class="pagebreak"></div>

# Quest 5 challenge · Three Words

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest05Challenge.java`

`cast spark d` has three words. Split it into all three.

## Input

One line: a command with exactly three words and one space between them.

## Output

For the first case in the table:

```
Verb: [cast]
Spell: [spark]
Way: [d]
```

- Each of the three words, in order, inside square brackets.

## The cases the tests use

| command | Verb | Spell | Way |
|---|---|---|---|
| cast spark d | `[cast]` | `[spark]` | `[d]` |
| cast quake w | `[cast]` | `[quake]` | `[w]` |
| cast zap a | `[cast]` | `[zap]` | `[a]` |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. The rest of the command is a String too. Everything you did to the command you can do to it.
2. Positions inside the rest start again at 0.
