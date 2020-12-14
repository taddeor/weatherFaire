package com.weather.service.impl;

import com.weather.ConfigTest;
import com.weather.api.ForecastWeatherApi;
import com.weather.bean.prediction.Prediction;
import com.weather.response.WeatherAggregateResponse;
import com.weather.utils.JacksonUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ContextConfiguration(classes = ConfigTest.class)
class WeatherOutSideWorkHoursTest {
    @Autowired
    WeatherOutSideWorkHours workHours;
    @MockBean
    ForecastWeatherApi forecastWeatherApi;
    @Test
    void prediction() throws Exception {

        var prediction = JacksonUtils.create("/json/precondition.json", Prediction.class);
        Mockito.when(forecastWeatherApi.predictionCityWeather(any())).thenReturn(prediction);
        var milan = workHours.callServiceWeather("Milan");
        assertNotNull(milan);
        assertNotNull(milan.getOutWorkHours());
        assertEquals(milan.getOutWorkHours().size(),13);
    }



}