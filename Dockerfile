FROM openjdk:8-jdk

RUN apt-get update && apt-get install -y mysql-server
RUN service mysql start && mysqladmin -u root password 'dev' && mysqladmin -u root create retail_stock

ENV PROJECT_PATH=/root/project
COPY build.gradle gradlew $PROJECT_PATH/
COPY src $PROJECT_PATH/src/
COPY gradle $PROJECT_PATH/gradle/

WORKDIR $PROJECT_PATH