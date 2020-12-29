#!/bin/bash

echo "--- Rebuilding \"business-logic\", \"mongodb\", \"productive-data-service\" and \"batch-process\":"
./stop.sh

echo "+ removing containers..."
docker container rm business-logic mongodb productive-data-service batch-process
echo "+ removing images..."
docker image rm business-logic mongodb productive-data-service batch-process

cd ./business-logic
echo "+ building \"business-logic\" executable..."
mvn clean install
echo "+ building \"business-logic\" image..."
docker build -t business-logic .
echo "+ creating \"business-logic\" container..."
docker create -p 8081:8081 --name business-logic business-logic
cd ./..

cd ./mongodb
echo "+ building \"mongodb\" image..."
docker build -t mongodb .
echo "+ creating \"mongodb\" container..."
docker create -p 27017:27017 --name mongodb mongodb
cd ./..

cd ./productive-data-service
echo "+ building \"productive-data-service\" executable..."
mvn clean install
echo "+ building \"productive-data-service\" image..."
docker build -t productive-data-service .
echo "+ creating \"productive-data-service\" container..."
docker create -p 8082:8082 --name productive-data-service productive-data-service
cd ./..

cd ./batch-process
echo "+ building \"batch-process\" executable..."
mvn clean install
echo "+ building \"batch-process\" image..."
docker build -t batch-process .
echo "+ creating \"batch-process\" container..."
docker create -p 8083:8083 --name batch-process batch-process
cd ./..