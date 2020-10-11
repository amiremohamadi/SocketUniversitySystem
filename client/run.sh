#!/bin/sh

# Only use this file if you are using a linux distro
# Windows users must use run.bat

# List of files in order to be compiled
commands="clean compile test run"
files_in_order=(
# Add new java files here (if needed)
    "src/Main.java"
)

usage() {
    echo "usage: ./run.sh [$commands]";
}


# Check number of arguments and validate
if [[ "$#" -ne 1 || $(echo $commands | grep -wL "$1") ]]; then
    usage;
    exit 2;
fi

# Do each flag in order
# Clean: remove prev .class files
for class_file in $(find -name *.class); do
    echo "remove $class_file";
    rm $class_file;
done

if [[ "$1" == "clean" ]]; then
    exit 0;
fi

# Compile to java class, consider dependancies
# If you need to add new file, just add regex/filename to
# files_in_order variable (top of this file)
for java_file in "${files_in_order[@]}"; do
    echo "comiling $java_file";
    javac -cp src $java_file;
done

if [[ "$1" == "compile" ]]; then
    exit 0;
fi


