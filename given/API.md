# The given classes

These classes are inside `given/crawler.jar`. You call them. You do not need to see inside them, and on the AP exam you never can: a free response question hands you a page like this one and nothing more.

A method marked `void` changes the object and gives nothing back. Any other method returns a value of the type named in front of it, and that value is lost unless you store it, print it or pass it on.

## Combat

Static methods. Call them on the class name: `Combat.damage(4, 1)`. No object is needed.

| | what it does |
|---|---|
| `static int damage(int attackPower, int defense)` | ORDER MATTERS. damage(4, 1) is 3. damage(1, 4) is 1. Both compile. A hit always does at least 1. |
| `static int clamp(int value, int low, int high)` | Forces value to stay from low to high, including both ends. |
| `static int roll(int low, int high)` | A random whole number from low to high, including both ends. |
| `static int distance(int row1, int col1, int row2, int col2)` | How many squares apart two places are, counting across and down. Never negative. |
| `static int bossPhase(int health, int maxHealth)` | 1 above two thirds of its health, 2 above one third, 3 at one third or less. |
| `static int shockwaveEvery(int phase)` | Turns between shockwaves in that phase. 0 means never. |
| `static int shotPower(String ammo)` | How hard that ammo hits: an arrow 3, a stone 1. |
| `static int shotRange(String ammo)` | How many squares that ammo flies: an arrow 6, a stone 3. |

## Hero

One member of the party. Make one with `new`.

| | what it does |
|---|---|
| `new Hero(String heroName, int startHealth, int attackPower)` | Health starts full. The most health anyone can have is whatever they start with. |
| `String getName()` | Returns the name. |
| `int getHealth()` | Returns the health. |
| `int getMaxHealth()` | The most health this hero can have. |
| `int getAttack()` | Returns the attack. |
| `boolean isAlive()` | True while health is above 0. |
| `void takeDamage(int amount)` | Health never goes below zero. |
| `int heal(int amount)` | Health never goes above the maximum. Returns how much was really gained. |
| `void setMaxHealth(int newMax)` | Changes the most health this hero can have. Health above the new maximum is cut down to it. |
| `String describe()` | One line about this hero, such as `Rook (10/10 health, attack 4)`. |

## Monster

One monster. The boss is a Monster too.

| | what it does |
|---|---|
| `new Monster(String monsterName, int startHealth, int attackPower, int startRow, int startCol)` | Health starts full. The row comes before the column. |
| `String getName()` | Returns the name. |
| `int getHealth()` | Returns the health. |
| `int getMaxHealth()` | The health it started with. |
| `int getAttack()` | Returns the attack. |
| `int getRow()` | Returns the row. |
| `int getCol()` | Returns the column. |
| `boolean isAlive()` | True while health is above 0. |
| `boolean isAt(int someRow, int someCol)` | True when the monster stands on that row and column. |
| `void takeDamage(int amount)` | Health never goes below zero. |
| `void raiseAttack(int amount)` | An enraged boss hits harder. |
| `String describe()` | One line about this monster, such as `Grub (4 health, attack 2)`. |

## Spell

One spell. It has two constructors.

| | what it does |
|---|---|
| `new Spell(String spellName, int manaCost, String spellKind)` | A spell of a kind the game already knows: "spark", "mend" or "quake". The cost is forced into the range 1 to 6. |
| `new Spell(String spellName, int manaCost, int nearDamage, int farDamage)` | Your own spell. Its kind is "bolt". It does nearDamage to a monster 2 squares away or closer, and farDamage beyond that. The cost is forced into the range 1 to 6. |
| `String getName()` | Returns the name. |
| `int getCost()` | Returns the cost. |
| `String getKind()` | Returns the kind. |
| `boolean isAimed()` | Does this spell need a direction? |
| `boolean canCast(int mana)` | True when that much mana is enough for this spell. |
| `int damageAt(int distance)` | Damage to a monster that many squares away. A spark always does 4. A bolt does its near or far damage, forced into the range 0 to 9. |
| `boolean isFair()` | True when neither damage is more than cost * 3. |
| `String describe()` | One line about this spell, such as `zap (2 mana)`. |

## The other classes

`Party`, `Inventory`, `Powerup`, `Powerups`, `DialogueLine`, `Dialogue`, `Level` and `Game` are in the jar too. The game uses them, and the fall quests do not. From January you replace the given classes with your own, one at a time.
