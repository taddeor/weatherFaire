package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "from")
public class WeatherOutSideResponse implements Serializable {
     private List<WeatherOutWorkHour> outWorkHours;
}
