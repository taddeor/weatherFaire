FROM openjdk:11-jre

#RUN apk update && apk add bash && rm -rf /var/cache/apk/*

RUN apt-get update && \
    apt-get install -y \
        locales && \
    apt-get install -y \
        apt-utils &&\
    rm -r /var/lib/apt/lists/*

RUN sed -i -e 's/# en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen && \
    sed -i -e 's/# it_IT.UTF-8 UTF-8/it_IT.UTF-8 UTF-8/' /etc/locale.gen && \
    dpkg-reconfigure --frontend=noninteractive locales
ENV LANG it_IT.UTF-8

ENV TZ=Europe/Rome
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD tmp/weather.jar /app/weather.jar
ADD tmp/startBoot.sh  /app/startBoot.sh
WORKDIR /app

ENTRYPOINT ["/app/startBoot.sh",  "weather" ,"8080","docker"]