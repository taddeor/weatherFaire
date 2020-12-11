package com.weather.service.impl;

import com.weather.api.DetailWeatherApi;
import com.weather.api.WeatherApi;
import com.weather.bean.detail.Hourly;
import com.weather.bean.detail.WeatherDetail;
import com.weather.bean.weather.Coord;
import com.weather.bean.weather.Weather;
import com.weather.response.WeatherResponse;
import com.weather.service.WeatherAggregator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherAggregatorImpl implements WeatherAggregator {
    private final WeatherApi weatherApi;
    private final DetailWeatherApi detailWeatherApi;
    @Override
    public WeatherResponse tempWorkHour(String city) {
        var weather = weatherApi.cityWeather(city);
        var coord = weather.getCoord();
        var weatherDetail = detailWeatherApi.detailCityWeather(coord.getLon(), coord.getLat(), List.of());

        for (Hourly hourly : weatherDetail.getHourly()) {
            var dateTime = hourly.getDateTime();
            log.info("DateTiem {}" ,dateTime);
        }


        return null;
    }
}
