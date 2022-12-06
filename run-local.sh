#!/usr/bin/env sh

cd microservice
gradle clean buildImage 

docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f
