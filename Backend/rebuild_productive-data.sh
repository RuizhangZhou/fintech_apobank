#!/bin/bash

echo "--- Rebuilding \"mongodb\" and \"productive-data-service\":"
./stop_productive-data.sh

echo "+ removing containers..."
docker container rm mongodb productive-data-service
echo "+ removing images..."
docker image rm mongodb productive-data-service
echo "+ attempting to delete all unused docker volumes..."
docker volume prune

echo "+ creating docker network \"pdsn\"..."
docker network create pdsn

cd ./mongodb
echo "+ building \"mongodb\" image..."
docker build -t mongodb .
echo "+ creating \"mongodb\" container..."
docker create --network=pdsn -p 27017:27017 --name mongodb mongodb
cd ./..

cd ./productive-data-service
echo "+ building \"productive-data-service\" executable..."
mvn clean install -DskipTests
echo "+ building \"productive-data-service\" image..."
docker build -t productive-data-service .
echo "+ creating \"productive-data-service\" container..."
docker create --network=pdsn -p 8082:8082 --name productive-data-service productive-data-service
cd ./..
