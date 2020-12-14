package com.weather.controller;

import com.weather.factory.Resolver;
import com.weather.response.WeatherAggregateResponse;
import com.weather.response.WeatherOutSideResponse;
import com.weather.response.WeatherResponse;
import com.weather.service.WeatherAggregator;
import com.weather.service.WeatherType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(tags = @Tag(name = "Weather", description = "Weather service"),
        security = {@SecurityRequirement(name = "bearerAuth")})
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final Resolver resolver;

    @Operation(
            tags = "Weather",
            description = "Endpoint to get an prediction for nex three days in work hours"
    )
    @GetMapping(value = "prediction-work-hour/{city}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public WeatherResponse predictionWorkHour(@PathVariable(value = "city") String city){
        var weather = resolver.resolve(WeatherType.WORKHOUR).callServiceWeather(city);
        log.info("Response [{}]",weather);
        return(weather instanceof WeatherResponse )?(WeatherResponse) weather:null;
    }

    @Operation(
            tags = "Weather",
            description = "Endpoint to get an prediction for nex three days out side work hours"
    )
    @GetMapping(value = "prediction-outside-work-hour/{city}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public WeatherOutSideResponse predictionOutSideWorkHour(@PathVariable(value = "city") String city){
        var weather = resolver.resolve(WeatherType.WORKOUTSIDEHOUR).callServiceWeather(city);
        log.info("Response [{}]",weather);
        return (weather instanceof WeatherOutSideResponse )?(WeatherOutSideResponse) weather:null;
    }


    @Operation(
            tags = "Weather",
            description = "Endpoint to get an prediction for nex three days aggregate in and aout side work hours"
    )
    @GetMapping(value = "prediction-aggregate/{city}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public WeatherAggregateResponse predictionAggregate(@PathVariable(value = "city") String city){
        var weather = resolver.resolve(WeatherType.AGGREGATE).callServiceWeather(city);
        log.info("Response [{}]",weather);
        return (weather instanceof WeatherAggregateResponse )?(WeatherAggregateResponse) weather:null;
    }
}
