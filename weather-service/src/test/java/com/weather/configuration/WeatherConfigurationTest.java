package com.weather.configuration;

import com.weather.ConfigTest;
import com.weather.factory.Resolver;
import com.weather.service.WeatherAggregator;
import com.weather.service.WeatherType;
import com.weather.service.impl.WeatherAggregateWorkHours;
import com.weather.service.impl.WeatherOutSideWorkHours;
import com.weather.service.impl.WeatherWorkOur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ContextConfiguration(classes = ConfigTest.class)
public class WeatherConfigurationTest {
    @Autowired
    Resolver resolver;
    @Test
    void resolver() {
        var resolve = resolver.resolve(WeatherType.WORKHOUR);
        assertNotNull(resolve);
        assertTrue((resolve instanceof WeatherWorkOur));
        var resolve1 = resolver.resolve(WeatherType.WORKOUTSIDEHOUR);
        assertNotNull(resolve1);
        assertTrue((resolve1 instanceof WeatherOutSideWorkHours));
        var resolve2 = resolver.resolve(WeatherType.AGGREGATE);
        assertNotNull(resolve2);
        assertTrue((resolve2 instanceof WeatherAggregateWorkHours));
    }
}