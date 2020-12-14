package com.weather.controller;

import com.weather.api.ForecastWeatherApi;
import com.weather.config.ConfigTest;
import com.weather.factory.Resolver;
import com.weather.response.WeatherAggregateResponse;
import com.weather.response.WeatherOutSideResponse;
import com.weather.response.WeatherResponse;
import com.weather.service.impl.WeatherAggregateWorkHours;
import com.weather.service.impl.WeatherOutSideWorkHours;
import com.weather.service.impl.WeatherWorkOur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ConfigTest.class,
        WeatherAggregateWorkHours.class,
        WeatherOutSideWorkHours.class,
        WeatherWorkOur.class, ForecastWeatherApi.class})
class WeatherControllerTest {

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void predictionWorkHour() throws MalformedURLException {

        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/prediction-work-hour/milan").toString(), WeatherResponse.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());



    }

    @Test
    void predictionOutSideWorkHour() throws MalformedURLException {
        ResponseEntity<WeatherOutSideResponse> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/prediction-outside-work-hour/milan").toString(), WeatherOutSideResponse.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    void predictionAggregate() throws MalformedURLException {
        ResponseEntity<WeatherAggregateResponse> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/prediction-aggregate/milan").toString(), WeatherAggregateResponse.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }
}