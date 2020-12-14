package com.weather.service;

public interface WeatherAggregator<T> {
    WeatherType WEATHER_TYPE();

    T callServiceWeather(String city);
}
