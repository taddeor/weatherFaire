package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor(staticName = "from")
public class WeatherAggregateResponse {
    private List<WeatherOutWorkHour> outWorkHours;
    private List<WeatherWorkHour> workHours;
}
