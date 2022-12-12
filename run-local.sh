#!/usr/bin/env sh
cd monitor-service
gradle clean buildImage
cd ../

cd discovery-server
gradle clean buildImage
cd ../

cd microservice
gradle clean buildImage
cd ../

cd notification-service
gradle clean buildImage
cd ../

cd consumer-rabbitmq-service
gradle clean buildImage
cd ../

docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f
