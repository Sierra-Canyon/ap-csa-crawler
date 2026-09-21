# Quest 8 · Can I Cast

**Fri 9 October, in class. About 15 minutes. 8 points.**
File: `src/Quest08CanICast.java`

A spell can be cast when three things are all true: there is a spell, there is enough mana for
it, and the monster is close enough. The first of the three protects the other two. The stub
already reads the input and makes the spell, or leaves it `null`.

## Input

Three lines: the spell's name (`spark`, `quake` or `none`), the mana the party has, and the distance to the nearest monster.

## Output

For the first case in the table:

```
Can cast: true
Mana after: 4
```

- A spark reaches 4 squares. A quake reaches 2.
- **Can cast** is one `boolean`, built with `&&` and `||`: the spell is not `null`, and `canCast(mana)` says yes, and the monster is within that spell's reach. Use `getKind()` to tell the two spells apart.
- **Mana after** is the mana less the spell's `getCost()` when it can be cast, and the same mana when it cannot.

## The cases the tests use

| spell | mana | distance | Can cast | Mana after | points |
|---|---|---|---|---|---|
| spark | 6 | 3 | true | 4 | 1 |
| spark | 1 | 3 | false | 1 | 1 |
| spark | 6 | 5 | false | 6 | 1 |
| quake | 4 | 2 | true | 0 | 1 |
| quake | 3 | 1 | false | 3 | 1 |
| quake | 6 | 3 | false | 6 | 1 |
| none | 6 | 1 | false | 6 | 1 |
| spark | 2 | 4 | true | 0 | 1 |

## Where people lose points

- `spell.canCast(mana) && spell != null` crashes on the `none` row with a NullPointerException. Java tests the left side first, and only skips the right side when the left side already settles it.
- `&&` binds tighter than `||`. `a && b || c && d` already means `(a && b) || (c && d)`, and parentheses make it readable.
- The fourth and last rows sit exactly on a boundary. `<` and `<=` differ there and nowhere else.

## For the log

1. In the `none` row, which parts of your condition did Java evaluate, and which did it skip?
2. What single change to your condition would make the `none` row crash?
3. Which rows of the table would still pass if you wrote `<` where you meant `<=`?

<div class="pagebreak"></div>

# Quest 8 challenge · Does the Shot Fly

**Extra. Not graded. The checker marks it `extra`.**
File: `src/Quest08Challenge.java`

An arrow looks at the next square and decides what happens to it.

## Input

Two lines: the tile in the next square (one character, so `in.next()` reads it) and how many arrows the party has.

## Output

For the first case in the table:

```
Shot: blocked
```

- With no arrows, **Shot** is `no arrows`, whatever the tile is.
- A wall `#`, a person `N` and a door `+` all stop it: `blocked`.
- A monster `M` and a boss `B` are both `hits`.
- Anything else is `flies on`.

## The cases the tests use

| tile | arrows | Shot |
|---|---|---|
| `#` | 3 | blocked |
| N | 1 | blocked |
| + | 2 | blocked |
| M | 1 | hits |
| B | 5 | hits |
| . | 1 | flies on |
| `$` | 1 | flies on |
| M | 0 | no arrows |
| `#` | 0 | no arrows |

## Hints

Read one. Try again. Read the next one only if you are still stuck.

1. `tile.equals("#") || "N"` does not compile. Each side of `||` has to be a whole boolean expression on its own.
2. One of the four rules wins over all the others. Decide which, and test it first.
