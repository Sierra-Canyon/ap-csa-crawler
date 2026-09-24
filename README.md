# Dungeon Crawler · AP CSA

The same dungeon the AP CSP class plays, written in Java. This repository is yours for the year.
In the fall you call the game's classes from `main`, one short quest at a time. In November you
write a small game of your own in one `main`. From January you replace the game's classes with
your own, one class at a time, and the game keeps running on them.

## Play it first

```
bash scripts/play.sh            the window
bash scripts/play.sh --text     the same game in the terminal
bash scripts/play.sh 4          start on level 4, which is your own level
```

In the window: arrows or WASD move. Walk into a monster to attack it and into a person to talk.
`F` shoots an arrow the way you are facing and `T` throws a stone. `1` to `4` cast spells. `P`
drinks a potion, `Tab` changes the leader, `M` turns the music off and `R` starts again. In a
conversation, `1` and `2` pick an answer and Enter moves on.

## What is where

| Folder | What it is | Do you edit it? |
|---|---|---|
| **`src/`** | Your programs. One file per quest, one per challenge, one for the project. | **Yes** |
| **`given/`** | The game's classes, packed in `crawler.jar`, the three levels, and **`API.md`**, the list of every method you may call. | No |
| **`my/`** | Four text files: your character, your spell, your level, your conversation. | Yes, whenever you like |
| **`assets/`** | The pictures and sounds. Replace any of them with a file of the same name. | Yes, whenever you like |
| **`tests/`** and **`tools/`** | The checker. | No |
| **`CREDITS.md`** | Where your art and music came from, and any help you had. | Yes |

## Doing a quest

1. Read the printed sheet. The table of cases on it is exactly what the checker runs.
2. Open the file in `src/` with the same number. Delete the line that says `NOT STARTED YET`.
3. Write your program inside `main`.
4. Run it yourself and type the input: `bash scripts/run.sh Quest05ParseTheCommand`
5. Run the checker on it: `bash scripts/check.sh Quest05`
6. Commit, with your log entry for the day.

`bash scripts/check.sh` with no quest named checks everything you have started.

**The checker reads labelled lines.** A quest says which labels to print, such as `Damage:`. The
checker finds the label and reads what follows it on that line. Spelling, capitals and the colon
all count. You may print anything else you like, prompts included. If a label appears more than
once, the last one is the one the checker reads, so print your answers last. The exception is a
label the spec says is printed once per row, such as `Drawn:`. Every line with that label counts.

**Each row of a quest's table is one point.** A quest with five rows is worth 5.

**`PASS`, `FAIL`, `TRY` and `....`** A `FAIL` line shows what was expected and what your program
printed. `TRY` is the same thing on a challenge, which is extra and not graded. `....` means not
started. After the checks comes the AP subset scan: Java that works and will not be available to
you on the exam in May.

**The debugger, from Fri 9/25.** Open VS Code on this folder, with the Java Extension Pack installed. Click in the margin to the left of a line number so a red dot appears, then press **F5** and pick **Run this file**, **Check one quest** (it asks for a quest name, such as `Quest04`), or **Play the game**. The program stops before the red dot, with the line in yellow; the **Variables** pane on the left shows what every variable holds, and two names for one object show the same id. **F10** runs one line, **F11** steps into a method call, **Shift+F11** steps back out, **F5** continues, **Shift+F5** stops. Every spec has a line that starts "Stuck on a row?" that says where the red dot goes.

**The second page of every spec is a challenge with hints.** Read one hint, try again, and read the
next only if you are still stuck.

## Updating from the template

When the template gets a fix, it does not reach your repository by itself. The one-page sheet "Updating from the template" has the commands; here they are again. Every command runs in a terminal inside your repository folder.

Once, the first time only (with nothing uncommitted):

```
git remote add template https://github.com/Sierra-Canyon/ap-csa-crawler.git
git fetch template --tags
git checkout main
git merge -s ours --allow-unrelated-histories -m "Link template history" baseline
git push
```

Every time there is an update (with nothing uncommitted):

```
git checkout main
git pull --no-rebase template main
git push
git checkout development
git merge main
git push
```

## Making the game yours

Nothing in `my/` or `assets/` is graded. The four files in `my/` explain themselves at the top.
The game checks them when it starts and tells you in plain words what is wrong: a level with no
door, a character that is too strong, a conversation line that leads nowhere.

## Help, and what kind

**Your Java is yours.** You may ask a chatbot to explain an error message or to explain code you
already wrote. You may not turn in code you did not write. This is the same rule as the syllabus.

**Art and music are different.** You may use the pictures and sounds that came with the game, make
your own, or use a chatbot to make them. Art and music are not graded. **Saying where they came
from is.** Every picture, song and chatbot you used goes in `CREDITS.md`.

**What your characters say is your writing**, like the rest of your Java.

## If something breaks

**It does not compile.** Read the first error. It names a file and a line number. The errors after
it are usually consequences of the first.

**The checker says your program asked for more input than the test provided.** Count your `scanner.next`
calls against the Input section of the spec. In a loop that reads until `quit`, check how you
compare the command with `"quit"`.

**The checker says your program was still running after 10 seconds, or that it printed more than a
million characters.** A loop never ends.

**`play.sh` says LEVEL PROBLEM or DIALOGUE PROBLEM.** It names the file and what is wrong with it.
The game will not start on a level that cannot be finished.
