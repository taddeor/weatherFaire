package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "from")
@NoArgsConstructor
public class WeatherOutSideResponse implements Serializable {
     private List<WeatherOutWorkHour> outWorkHours;
}
