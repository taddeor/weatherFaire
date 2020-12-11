package com.weather.controller;

import com.weather.api.WeatherApi;
import com.weather.bean.weather.Weather;
import com.weather.response.WeatherResponse;
import com.weather.service.WeatherAggregator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(tags = @Tag(name = "Weather", description = "Weather service"),
        security = {@SecurityRequirement(name = "bearerAuth")})
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherAggregator weatherAggregator;

    @Operation(
            tags = "Weather",
            description = "Endpoint to get an workhour"
    )
    @GetMapping(value = "workhour",produces = {MediaType.APPLICATION_JSON_VALUE})
    public WeatherResponse tempWorkHour(@RequestParam(value = "city") String city){
        var weather = weatherAggregator.tempWorkHour(city);
        log.info("Response [{}]",weather);
        return null;
    }

    @Operation(
            tags = "Weather",
            description = "Endpoint to get an outworkhour"
    )
    @GetMapping(value = "outworkhour",produces = {MediaType.APPLICATION_JSON_VALUE})
    public WeatherResponse tempOutWorkHour(){
    return null;
    }
}
