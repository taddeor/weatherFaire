#!/usr/bin/env bash
DIR="$( dirname $0 )"
cd "$DIR"

#mvn clean install

if [ "x$JAVA" = "x" ]; then
    if [ "x$JAVA_HOME" != "x" ]; then
        JAVA="$JAVA_HOME/bin/java"
    else
        JAVA="java"
    fi
fi


export JAVA_OPTS="$JAVA_OPTS -Dserver.host=8080 -Xms512m -Xmx512m -XX:MetaspaceSize=258m -XX:MaxMetaspaceSize=258m"

$JAVA -jar $JAVA_OPTS ./weather-boot/target/weather.jar