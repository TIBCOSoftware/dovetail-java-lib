#!/bin/bash
build_tag=$1

mvn package

echo "Building jar file"
mvn package -Djar.finalName=dovetail-runtime-${build_tag}
