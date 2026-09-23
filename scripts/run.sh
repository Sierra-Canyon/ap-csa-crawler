#!/bin/bash
# Run one of your programs yourself and type its input.
#   bash scripts/run.sh Quest05ParseTheCommand
#   bash scripts/run.sh DungeonInOneMain
cd "$(dirname "$0")/.."
# Windows (Git Bash) separates classpath entries with ; and everything else with :
case "$(uname -s)" in MINGW*|MSYS*|CYGWIN*) S=";" ;; *) S=":" ;; esac
if [ -z "$1" ]; then
  echo "Which program? For example: bash scripts/run.sh Quest05ParseTheCommand"
  exit 1
fi
rm -rf out
mkdir -p out
javac -g -cp given/crawler.jar -d out $(find src -name '*.java') || exit 1
java -cp "out${S}given/crawler.jar" "${1%.java}"
