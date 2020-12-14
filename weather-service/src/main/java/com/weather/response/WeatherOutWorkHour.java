package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "from")
@NoArgsConstructor
public class WeatherOutWorkHour {
    LocalDateTime dateTime;
    Double maxTemp;
    Double minTemp;
    Integer humidity;
}
