#!/bin/bash

echo "--- Starting \"batch-process\":"

cd ./batch-process
echo "+ building \"batch-process\" executable..."
mvn clean install -DskipTests
echo "+ executing \"batch-process\" executable..."
java -jar target/batch-process-0.0.1-SNAPSHOT.jar
