#!/bin/env sh
mkdir build && cd build
javac ../src/*.java
cd ..
jar -cf Reversi.jar build/*.class
