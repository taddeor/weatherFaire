package com.weather.configuration;

import com.weather.factory.Resolver;
import com.weather.service.WeatherAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Configuration
public class WeatherConfiguration {

    @Bean
    public Resolver resolver(List<WeatherAggregator> weatherAggregators){
        var collect = weatherAggregators.stream().collect(Collectors.toMap(WeatherAggregator::WEATHER_TYPE, Function.identity()));
        return Resolver.from(collect);
    }

}
