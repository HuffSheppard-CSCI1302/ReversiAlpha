#!/bin/env sh
mkdir build && cd build
javac ../src/*.java
jar -cf Reversi.jar ../src/*.class
rm *.class
