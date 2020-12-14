# weather case study
this is an case study for Faire 

it's an Spring Boot application with version Spring-Boot-Version 2.4.1 and JDK 11

Easy to use and build

To build project and start in a Docker container

to root project launch build.sh `` build with maven and prepare image for docker container``


The Boot application expose a swagger documentation http://localhost:8080/swagger-ui.html

expose three RESTFul service

RESTful min and max temperature and humidity during work hours

curl -X GET "http://localhost:8080/prediction-work-hour/{city}" -H  "accept: application/json"
Response
{
    "workHours": [
    {
    "dateTime": "2020-12-14T12:59:24.462Z",
    "maxTemp": 0,
    "minTemp": 0,
    "humidity": 0
    }
    ]
}

RESTful min and max temperature and humidity aggregate in work hours and outside hours

curl -X GET "http://localhost:8080/prediction-aggregate/{city}" -H  "accept: application/json"
Response
{
"outWorkHours": [
{
"dateTime": "2020-12-14T13:00:12.554Z",
"maxTemp": 0,
"minTemp": 0,
"humidity": 0
}
],
"workHours": [
{
"dateTime": "2020-12-14T13:00:12.554Z",
"maxTemp": 0,
"minTemp": 0,
"humidity": 0
}
]
}

RESTful min and max temperature and humidity during outside work hours

curl -X GET "http://localhost:8080/prediction-outside-work-hour/{city}" -H  "accept: application/json"
Response
{
"outWorkHours": [
{
"dateTime": "2020-12-14T12:59:24.446Z",
"maxTemp": 0,
"minTemp": 0,
"humidity": 0
}
]
}

