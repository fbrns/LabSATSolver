#!/bin/sh

if [ "$JAVA_HOME" == "" ]
then
    echo "NO JAVA_HOME SET"; exit 1
fi

./install-lingeling.sh

#
# Compile LabSAT solver
#
mvn package
mv target/LabSAT-0.0.1-jar-with-dependencies.jar solver.jar
