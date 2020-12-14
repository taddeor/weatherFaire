package com.weather.api;


import com.weather.WeatherTestConfiguration;
import com.weather.bean.prediction.Prediction;
import com.weather.configuration.WeatherClintConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * integration test with service openweather API
 */

@SpringBootTest(properties = {"test"},
        classes = {WeatherApi.class,
                   DetailWeatherApi.class,
                   ForecastWeatherApi.class,
                   WeatherClintConfiguration.class,
                   WeatherTestConfiguration.class})
public class WeatherApiTest {
    @Autowired
    WeatherApi weatherApi;
    @Autowired
    DetailWeatherApi detailWeatherApi;
    @Autowired
    ForecastWeatherApi forecastWeatherApi;
    /**
     * call service city Weather for a single city
     */
    @Test
    public void cityWeather() {
        Double lon = 9.19D;
        Double lat = 45.46D;

        var weather = weatherApi.cityWeather("Milan");
        var coord = weather.getCoord();
         assertNotNull( coord);
         assertEquals(lon,coord.getLon());
         assertEquals(lat,coord.getLat());
         assertEquals("Milan",weather.getName());
    }

    /**
     * call service detail
     */
    @Test
    public void detailCityWeather(){
        var weather = weatherApi.cityWeather("Milan");
        var coord = weather.getCoord();
        var weatherDetail = detailWeatherApi.detailCityWeather(coord.getLon(), coord.getLat(), List.of());
        assertNotNull( weatherDetail );
    }

    /**
     * call service forecast integration test
     */
    @Test
    public void forecastService(){
        var milan = forecastWeatherApi.predictionCityWeather("milan");
        assertNotNull(milan);
        assertNotNull(
                milan.getList()
        );
    }
}