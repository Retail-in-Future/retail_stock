#!/bin/sh
cd $(dirname $0)
cd ../../
APP_NAME=$(basename $(pwd))

echo ">>>========= build docker image with latest source code"
DOCKER_IMAGE=`echo "local/$APP_NAME:latest" | tr "[:upper:]" "[:lower:]"`
docker build -t $DOCKER_IMAGE ./

mkdir -p build
docker rm retail_stock_mysql
docker run --name retail_stock_mysql -e MYSQL_ROOT_PASSWORD=dev -e MYSQL_ROOT_HOST='%' -e MYSQL_DATABASE=retail -d mysql/mysql-server:latest
docker run -i -e DOCKER_HOST_USERID="$(id -u):$(id -g)" -v gradle-cache:/root/.gradle -v $(pwd)/build:/root/project/build --rm --link retail_stock_mysql:mysql $DOCKER_IMAGE /bin/bash << "COMMANDS"
set -o errexit -o nounset \
\
&& echo ">>>========= run build in container" \
&& rm -rf build/* \
&& sed -i -e "s/localhost:3306\/retail/$MYSQL_PORT_3306_TCP_ADDR:$MYSQL_PORT_3306_TCP_PORT\/$MYSQL_ENV_MYSQL_DATABASE/g" src/main/resources/application.yml \
&& sed -i -e "s/dev/$MYSQL_ENV_MYSQL_ROOT_PASSWORD/g" src/main/resources/application.yml \
&& ./gradlew build --info \
\
&& echo ">>>========= changing artifacts owner" \
&& chown --recursive $DOCKER_HOST_USERID build
COMMANDS

docker stop retail_stock_mysql
