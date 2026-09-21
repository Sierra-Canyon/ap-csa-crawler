#!/bin/bash
# Compile your quests against the given classes, run the checker, then scan for Java off the AP subset.
#   bash scripts/check.sh            everything
#   bash scripts/check.sh Quest01    just one quest
cd "$(dirname "$0")/.."
rm -rf out
mkdir -p out
echo "Compiling..."
if ! javac -g -cp given/crawler.jar -d out $(find src tools tests -name '*.java') 2>&1; then
  echo
  echo "=============================================================="
  echo "It does not compile, so nothing below could run."
  echo "Read the FIRST error. The rest are usually consequences of it."
  echo "=============================================================="
  exit 1
fi
echo "Compiles."
echo
java -cp out:given/crawler.jar RunAll "$@"
GRADE=$?
echo
java -cp out:given/crawler.jar ApSubset src
exit $GRADE
