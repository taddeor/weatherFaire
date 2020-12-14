package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor(staticName = "from")
@NoArgsConstructor
public class WeatherAggregateResponse {
    private List<WeatherOutWorkHour> outWorkHours;
    private List<WeatherWorkHour> workHours;
}
