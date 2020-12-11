
package com.weather.bean.detail;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "temp",
    "feels_like",
    "pressure",
    "humidity",
    "dew_point",
    "uvi",
    "clouds",
    "visibility",
    "wind_speed",
    "wind_deg",
    "weather",
    "pop",
    "rain"
})
@Data
public class Hourly {

    @JsonProperty("dt")
    public Integer dt;

    @JsonProperty("temp")
    public Double temp;
    @JsonProperty("feels_like")
    public Double feelsLike;
    @JsonProperty("pressure")
    public Integer pressure;
    @JsonProperty("humidity")
    public Integer humidity;
    @JsonProperty("dew_point")
    public Double dewPoint;
    @JsonProperty("uvi")
    public Integer uvi;
    @JsonProperty("clouds")
    public Integer clouds;
    @JsonProperty("visibility")
    public Integer visibility;
    @JsonProperty("wind_speed")
    public Double windSpeed;
    @JsonProperty("wind_deg")
    public Integer windDeg;
    @JsonProperty("weather")
    public List<Weather_> weather = null;
    @JsonProperty("pop")
    public Integer pop;
    @JsonProperty("rain")
    public Rain rain;


    public LocalDateTime getDateTime(){

        LocalDateTime date =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(dt), ZoneId.ofOffset("" ,ZoneOffset.UTC));
        var localTime = LocalTime.ofInstant(Instant.ofEpochMilli(dt), ZoneId.systemDefault());
        return date;
    }
}
