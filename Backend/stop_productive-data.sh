#!/bin/bash

echo "+ stopping \"mongodb\" and \"productive-data-service\" container..."
docker container stop mongodb productive-data-service