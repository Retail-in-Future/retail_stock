#!/bin/sh
cd $(dirname $0)
cd ../../
APP_NAME=$(basename $(pwd))

echo ">>>========= build docker image with latest source code"
DOCKER_IMAGE=`echo "local/$APP_NAME:latest" | tr "[:upper:]" "[:lower:]"`
docker build -t $DOCKER_IMAGE ./

echo ">>>========= run application in docker container"
docker run -it -v gradle-cache:/root/.gradle -p 8081:8081 --rm $DOCKER_IMAGE ./gradlew bootrun

