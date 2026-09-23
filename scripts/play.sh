#!/bin/bash
# Play the game.
#   bash scripts/play.sh            the window
#   bash scripts/play.sh --text     the same game in the terminal
#   bash scripts/play.sh 4          start on level 4, which is my/level.txt
# From January, a class of yours in src/ replaces the given class with the same name.
cd "$(dirname "$0")/.."
# Windows (Git Bash) separates classpath entries with ; and everything else with :
case "$(uname -s)" in MINGW*|MSYS*|CYGWIN*) S=";" ;; *) S=":" ;; esac
rm -rf out
mkdir -p out
javac -g -cp given/crawler.jar -d out $(find src -name '*.java') || exit 1
java -cp "out${S}given/crawler.jar" Play "$@"
