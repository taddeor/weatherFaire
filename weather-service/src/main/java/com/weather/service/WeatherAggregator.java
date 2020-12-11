package com.weather.service;

import com.weather.response.WeatherResponse;

public interface WeatherAggregator {
    WeatherResponse tempWorkHour(String city);
}
