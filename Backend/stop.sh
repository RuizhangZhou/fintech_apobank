#!/bin/bash

echo "+ stopping \"business-logic\", \"mongodb\", \"productive-data-service\" and \"batch-process\" container..."
docker container stop business-logic mongodb productive-data-service batch-process