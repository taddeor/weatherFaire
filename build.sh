#!/usr/bin/env bash
DIR="$( dirname $0 )"
cd "$DIR"

mvn clean install

mkdir -p tmp

cp ./weather-boot/target/weather.jar tmp

cp ./startBoot.sh tmp

docker build -t=weather:latest --rm=true .

rm -r tmp

docker-compose up -d