#!/bin/bash

echo "--- Starting \"mongodb\" and \"productive-data-service\":"

echo "+ starting \"mongodb\" container..."
docker container start -i mongodb &

echo "+ starting \"productive-data-service\" container..."
docker container start -i productive-data-service &
