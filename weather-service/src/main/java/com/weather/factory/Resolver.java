package com.weather.factory;

import com.weather.service.WeatherAggregator;
import com.weather.service.WeatherType;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * factory resolve implementation of WeatherAggregator interface
 */
@AllArgsConstructor(staticName = "from")
public class Resolver {
    public  WeatherAggregator resolve(WeatherType weatherType){
        return weatherAggregatorMap.get(weatherType);
    }
    private Map<WeatherType, WeatherAggregator> weatherAggregatorMap ;
}
