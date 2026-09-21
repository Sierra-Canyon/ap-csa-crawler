# Quest 4 · One Round

**Tue 29 September, in class. About 15 minutes. 3 points.**
File: `src/Quest04OneRound.java`

One round of a fight: the hero hits, the monster hits back, the hero drinks a potion. Some of the
methods you call are `void` and some return a value.

## Input

Seven lines: the hero's name (one word), health and attack, the monster's name (one word), health and attack, and how much the potion heals. The stub already reads them.

## Output

For the first case in the table:

```
Monster now: 5
Hero now: 10
Gained: 2
Monster alive: true
```

- The hero hits first. The damage is `Combat.damage` with the hero's attack and a defense of 0, passed straight to the monster's `takeDamage`.
- The monster hits back. The damage is `Combat.damage` with the monster's attack and a defense of 1. There is no `if` yet, so it hits back even at 0 health.
- The hero drinks the potion with `heal`. **Gained** is what `heal` returned, which is not always the size of the potion.
- **Monster now** and **Hero now** are each one's health at the end. **Monster alive** is what `isAlive()` returns.

## The cases the tests use

| hero | health | attack | monster | health | attack | potion | Monster now | Hero now | Gained | Monster alive | points |
|---|---|---|---|---|---|---|---|---|---|---|---|
| Rook | 10 | 4 | Grub | 9 | 3 | 5 | 5 | 10 | 2 | true | 1 |
| Mote | 7 | 3 | Ogre | 3 | 6 | 9 | 0 | 7 | 5 | false | 1 |
| Brannoch | 10 | 5 | Bat | 20 | 1 | 4 | 15 | 10 | 1 | true | 1 |

## Where people lose points

- `hero.heal(potion);` on its own line heals the hero and throws the answer away. Then Gained has nothing to print except the potion's size, which is wrong in every row of the table.
- `int left = monster.takeDamage(4);` does not compile. `takeDamage` is `void`: it changes the monster and returns nothing.
- In the third row the Bat's attack is 1 and the defense is 1. `Combat.damage` never returns less than 1.

## For the log

1. Which of the methods you called today are void, and how can you tell from `given/API.md`?
2. In the first row the potion heals 5 and Gained is 2. Where did the other 3 go?
3. The Ogre in the second row is at 0 health and still hits Mote for 5. Which line of yours does that, and what would have to change?

<div class="pagebreak"></div>

# Quest 4 challenge · Cast It

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest04Challenge.java`

Make your own bolt spell and cast it at a Grub, in one statement: the spell works out the damage and the monster takes it.

## Input

Five whole numbers, each on its own line: the spell's mana cost, its damage up close, its damage far away, the distance to the Grub, and the Grub's health.

## Output

For the first case in the table:

```
Spell: zap (2 mana)
Fair: true
Hit for: 5
Grub now: 4
```

- The spell is called `zap` and uses the constructor with four parameters.
- **Spell** is its `describe()`. **Fair** is its `isFair()`. **Hit for** is its `damageAt` for that distance.
- One statement makes the Grub take that damage. **Grub now** is its health afterwards.

## The cases the tests use

| cost | near | far | distance | health | Spell | Fair | Hit for | Grub now |
|---|---|---|---|---|---|---|---|---|
| 2 | 5 | 2 | 1 | 9 | zap (2 mana) | true | 5 | 4 |
| 2 | 5 | 2 | 4 | 9 | zap (2 mana) | true | 2 | 7 |
| 1 | 4 | 1 | 2 | 3 | zap (1 mana) | false | 4 | 0 |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. `damageAt` returns a number. `takeDamage` needs a number.
2. A call that returns a value can sit inside another call's parentheses.
3. Two squares away counts as up close. The rule is in `given/API.md`.
