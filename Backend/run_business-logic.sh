#!/bin/bash

echo "--- Starting \"business-logic\":"

cd ./business-logic
echo "+ building \"business-logic\" executable..."
mvn clean install -DskipTests
echo "+ executing \"business-logic\" executable..."
java -jar target/business-logic-0.0.1-SNAPSHOT.jar
