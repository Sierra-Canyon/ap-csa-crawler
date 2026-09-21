# Quest 1 · Roll for Damage

**Wed 23 September, in class. About 15 minutes. 5 points.**
File: `src/Quest01RollForDamage.java`

The game's arithmetic lives in a class called `Combat`. Its methods are static, so you call them
on the class name, the same way you call `Math.abs`. The list of them is in `given/API.md`.

## Input

Six whole numbers, each on its own line: the hero's attack, the monster's defense, the hero's row, the hero's column, the monster's row, the monster's column. The stub already reads them.

## Output

For the first case in the table:

```
Damage: 3
Swapped: 1
Steps: 7
Straight line: 5.0
Roll: 5
```

- **Damage** is `Combat.damage` with the attack first and the defense second.
- **Swapped** is the same call with the two arguments the other way around.
- **Steps** is `Combat.distance` from the hero's square to the monster's square.
- **Straight line** is the distance a bird would fly: the square root of (rows apart squared plus columns apart squared). Use `Math.sqrt` and `Math.pow`. It is a `double`, and anything within 0.01 passes.
- **Roll** is one roll of a six sided die: a whole number from 1 to 6, from `Math.random()`. It is different every run, so the table cannot list it. The checker runs your program 40 times and looks at all 40 rolls.

## The cases the tests use

| attack | defense | hero row | hero col | monster row | monster col | Damage | Swapped | Steps | Straight line | points |
|---|---|---|---|---|---|---|---|---|---|---|
| 4 | 1 | 1 | 1 | 4 | 5 | 3 | 1 | 7 | 5.0 | 1 |
| 2 | 5 | 0 | 0 | 0 | 3 | 1 | 3 | 3 | 3.0 | 1 |
| 5 | 0 | 6 | 2 | 1 | 2 | 5 | 1 | 5 | 5.0 | 1 |
| 3 | 3 | 2 | 7 | 3 | 8 | 1 | 1 | 2 | 1.41 | 1 |
| 40 runs: every Roll is from 1 to 6, and at least three different values appear |  |  |  |  |  |  |  |  |  | 1 |

## Where people lose points

- `Combat.damage(defense, attack)` compiles and runs. Java matches arguments to parameters by position, never by name.
- `(int) Math.random() * 6 + 1` is always 1. The cast happens before the multiplication, and `(int)` of anything below 1 is 0.
- `Math.pow` returns a `double` even when both arguments are whole numbers.

## For the log

1. What are Damage and Swapped for attack 2 and defense 5, and why are they different?
2. Which part of your Roll line decides that the smallest roll is 1 and not 0?
3. Why can Steps be an int while Straight line has to be a double?

<div class="pagebreak"></div>

# Quest 1 challenge · Any Dice

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest01Challenge.java`

Some monsters roll a die that does not start at 1. Write the random formula yourself, for any lowest and highest value. Do not call `Combat.roll`.

## Input

Two whole numbers, each on its own line: the lowest roll and the highest roll. The lowest is never above the highest.

## Output

For the first case in the table:

```
Values: 3
Roll: 4
```

- **Values** is how many different rolls there are, counting both ends.
- **Roll** is a random whole number from the lowest to the highest, both included. The checker runs your program 200 times with 3 and 5, and expects to see every one of 3, 4 and 5 and nothing else.

## The cases the tests use

| lowest | highest | Values |
|---|---|---|
| 3 | 5 | 3 |
| 1 | 20 | 20 |
| 4 | 4 | 1 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. Count the different whole numbers from 3 to 5, both ends included. That count is Values.
2. `Math.random()` times a whole number, cast to `int`, gives that many different values, and the first of them is 0.
3. What single number do you add so that 0, 1, 2 becomes 3, 4, 5?
