#!/bin/bash
# Play your project, Dungeon in One Main, on your own dungeon in my/dungeon.txt.
#   bash scripts/dungeon.sh          the map goes in, then you type the commands
#   bash scripts/dungeon.sh walk     the map goes in, then the WALK line from the file, then quit
cd "$(dirname "$0")/.."
# Windows (Git Bash) separates classpath entries with ; and everything else with :
case "$(uname -s)" in MINGW*|MSYS*|CYGWIN*) S=";" ;; *) S=":" ;; esac
rm -rf out
mkdir -p out
javac -g -cp given/crawler.jar -d out $(find src -name '*.java') || exit 1
lines=$(tr -d '\r' < my/dungeon.txt | grep -v '^[[:space:]]*//' | grep -v '^[[:space:]]*$')
rows=$(echo "$lines" | grep -vi '^[[:space:]]*\(HEALTH\|WALK\)' | tr -d ' ')
health=$(echo "$lines" | grep -i '^[[:space:]]*HEALTH' | head -1 | awk '{print $2}')
walk=$(echo "$lines" | grep -i '^[[:space:]]*WALK' | head -1 | sed 's/^[[:space:]]*[Ww][Aa][Ll][Kk][[:space:]]*//')
width=$(echo "$rows" | head -1 | awk '{print length($0)}')
map=$(echo "$rows" | tr -d '\n')
echo "Your dungeon, $width wide:"
echo "$rows"
if [ "$1" = "walk" ]; then
  printf '%s\n%s\n%s\n' "$width" "$map" "${health:-10}" > out/dungeon-input.txt
  for c in $walk quit; do echo "$c" >> out/dungeon-input.txt; done
  java -cp "out${S}given/crawler.jar" DungeonInOneMain < out/dungeon-input.txt
else
  echo "Type w, a, s or d (and f, if you did the extra), one per line. quit ends it."
  { printf '%s\n%s\n%s\n' "$width" "$map" "${health:-10}"; cat; } | java -cp "out${S}given/crawler.jar" DungeonInOneMain
fi
