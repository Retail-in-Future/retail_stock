#!/bin/sh
cd $(dirname $0)
cd ../../
APP_NAME=$(basename $(pwd))

echo ">>>========= build docker image with latest source code"
DOCKER_IMAGE=`echo "local/$APP_NAME:latest" | tr "[:upper:]" "[:lower:]"`
docker build -t $DOCKER_IMAGE ./

echo ">>>========= run application in docker container"
docker run -v gradle-cache:/root/.gradle --rm $DOCKER_IMAGE ./gradlew clean assemble