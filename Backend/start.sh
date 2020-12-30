#!/bin/bash

echo "--- Starting Backend:"

echo "+ starting \"business-logic\" container..."
docker container start -i business-logic &

echo "+ starting \"mongodb\" container..."
docker container start -i mongodb &

echo "+ starting \"productive-data-service\" container..."
docker container start -i productive-data-service &

echo "+ starting \"batch-process\" container..."
docker container start -i batch-process &
