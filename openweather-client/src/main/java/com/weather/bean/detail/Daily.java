
package com.weather.bean.detail;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "sunrise",
    "sunset",
    "temp",
    "feels_like",
    "pressure",
    "humidity",
    "dew_point",
    "wind_speed",
    "wind_deg",
    "weather",
    "clouds",
    "pop",
    "rain",
    "uvi"
})
@Data
public class Daily {

    @JsonProperty("dt")
    public Integer dt;
    @JsonProperty("sunrise")
    public Integer sunrise;
    @JsonProperty("sunset")
    public Integer sunset;
    @JsonProperty("temp")
    public Temp temp;
    @JsonProperty("feels_like")
    public FeelsLike feelsLike;
    @JsonProperty("pressure")
    public Integer pressure;
    @JsonProperty("humidity")
    public Integer humidity;
    @JsonProperty("dew_point")
    public Double dewPoint;
    @JsonProperty("wind_speed")
    public Double windSpeed;
    @JsonProperty("wind_deg")
    public Integer windDeg;
    @JsonProperty("weather")
    public List<Weather__> weather = null;
    @JsonProperty("clouds")
    public Integer clouds;
    @JsonProperty("pop")
    public Integer pop;
    @JsonProperty("rain")
    public Double rain;
    @JsonProperty("uvi")
    public Integer uvi;

}
