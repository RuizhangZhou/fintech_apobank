#!/bin/bash

echo "--- Resetting Docker contents:"
echo "+ stopping all running docker containers..."
docker stop $(docker ps -aq)
echo "+ deleting all docker containers"
docker container prune
echo "+ deleting all docker images"
docker image prune -a
echo "+ deleting all docker networks"
docker network prune

echo "+ creating docker network \"dsn\"..."
docker network create dsn