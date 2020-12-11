package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "from")
public class WeatherWorkHour {
    Double maxTemp;
    Double minTemp;
    Double humidity;
}
