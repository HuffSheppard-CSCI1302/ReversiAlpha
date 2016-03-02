#!/bin/env sh
## Script by Miles B Huff
echo "Building 'ReversiAlpha'..."
mkdir build && cd build
javac ../src/*.java
cd ..
jar -cf Reversi.jar build/*.class
#rm -rf build
echo "Done.  "
