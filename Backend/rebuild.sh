#!/bin/bash

echo "--- Rebuilding \"mongodb\":"
./stop.sh

echo "+ removing container..."
docker container rm mongodb
echo "+ removing image..."
docker image rm mongodb

cd ./mongodb
echo "+ building \"mongodb\" image..."
docker build -t mongodb .
echo "+ creating \"mongodb\" container..."
docker create -p 27017:27017 --name mongodb mongodb
cd ./..