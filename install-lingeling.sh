#!/bin/sh

#
# get and compile lingeling-ayv-86bf266-140429
#
cd lib
mkdir lingeling
cd lingeling

wget http://fmv.jku.at/lingeling/lingeling-ayv-86bf266-140429.zip 
unzip lingeling-ayv-86bf266-140429.zip

cd code
chmod 755 configure.sh mkconfig.sh
./configure.sh -fPIC
make lingeling
cd ../../..

#
# Compile JNI for LabSAT <-> lingeling
#
cd lib
gcc -I $JAVA_HOME/lib/ -I $JAVA_HOME/include/ -I $JAVA_HOME/include/linux/ \
-o libLingeling.so argumentation_sat_Lingeling.c -L./lingeling/code -llgl -fPIC -shared
mv libLingeling.so ..
cd ..