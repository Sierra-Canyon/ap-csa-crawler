# Quest 7 · First Encounter

**Wed 7 October, in class. About 15 minutes. 5 points.**
File: `src/Quest07FirstEncounter.java`

The hero swings once at a monster. A die decides how well it goes, and an `if` / `else if` /
`else` chain turns the roll into damage.

## Input

Five lines: the hero's name, the hero's attack, the monster's name, the monster's health, and the roll (1 to 6). A name can be two words, such as `Cave Bat`. The stub already reads them.

## Output

For the first case in the table:

```
Damage: 4
Health left: 5
Result: hit
```

- A roll of 1 is a **miss**: 0 damage.
- A roll of 6 is a **critical**: twice the hero's attack.
- Any other roll is a **hit**: the hero's attack.
- Make a `Monster` and give it the damage with `takeDamage`. **Health left** is its `getHealth()` afterwards.
- **Result** is `miss`, `hit` or `critical`. If the monster is no longer alive, Result is `defeated` instead, whatever the roll was.

## The cases the tests use

| hero | attack | monster | health | roll | Damage | Health left | Result | points |
|---|---|---|---|---|---|---|---|---|
| Rook | 4 | Grub | 9 | 3 | 4 | 5 | hit | 1 |
| Rook | 4 | Grub | 9 | 1 | 0 | 9 | miss | 1 |
| Rook | 4 | Grub | 9 | 6 | 8 | 1 | critical | 1 |
| Mote | 3 | Cave Bat | 5 | 6 | 6 | 0 | defeated | 1 |
| Mote | 3 | Cave Bat | 3 | 2 | 3 | 0 | defeated | 1 |

## Where people lose points

- The stub calls `in.nextLine()` once with nothing on the left of it. After `in.nextInt()` the rest of that line, which is only the Enter key, is still waiting, and that call throws it away. Without it the monster's name would be empty.
- Three separate `if` statements all run. A chain with `else if` stops at the first one that is true.
- Health left comes from the monster. `health - damage` is wrong in the fourth row, where it would be -1.

## For the log

1. For a roll of 6, which of your conditions were tested before one was true?
2. Why does the defeated check come after the chain and not inside it?
3. Which row of the table would catch a program that used three separate ifs, and what would it print?

<div class="pagebreak"></div>

# Quest 7 challenge · What Is in the Chest

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest07Challenge.java`

When the hero opens a chest the game rolls from 1 to 10 and looks the roll up in bands.

## Input

One whole number: the roll.

## Output

For the first case in the table:

```
Loot: potion
```

- 1 to 3 is a `potion`. 4 and 5 are `gold`. 6 is a `shield`. 7 and 8 are an `arrow`. 9 and 10 are a `stone`.
- Any other number is `nothing`.
- Use `<=` in the chain. No `&&` and no `||`: they are Friday's lesson, and this chain does not need them.

## The cases the tests use

| roll | Loot |
|---|---|
| 1 | potion |
| 3 | potion |
| 4 | gold |
| 5 | gold |
| 6 | shield |
| 7 | arrow |
| 8 | arrow |
| 9 | stone |
| 10 | stone |
| 0 | nothing |
| 11 | nothing |
| -4 | nothing |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. Test the bands in order, smallest first. By the time the second test runs, the first one has already failed, and that tells you something about the roll.
2. Decide where 0 and 11 are caught before you write the chain. They are at opposite ends.
