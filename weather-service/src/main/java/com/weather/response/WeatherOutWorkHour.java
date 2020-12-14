package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "from")
public class WeatherOutWorkHour {
    LocalDateTime dateTime;
    Double maxTemp;
    Double minTemp;
    Integer humidity;
}
