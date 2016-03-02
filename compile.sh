#!/bin/env sh
echo "Building 'ReversiAlpha'..."
mkdir build && cd build
javac ../src/*.java
cd ..
jar -cf Reversi.jar build/*.class
#rm -rf build
echo "Done.  "
