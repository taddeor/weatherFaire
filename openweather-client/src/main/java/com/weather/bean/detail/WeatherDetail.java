
package com.weather.bean.detail;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lat",
    "lon",
    "timezone",
    "timezone_offset",
    "current",
    "minutely",
    "hourly",
    "daily"
})
@Data
public class WeatherDetail {

    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lon")
    public Double lon;
    @JsonProperty("timezone")
    public String timezone;
    @JsonProperty("timezone_offset")
    public Integer timezoneOffset;
    @JsonProperty("current")
    public Current current;
    @JsonProperty("minutely")
    public List<Minutely> minutely = null;
    @JsonProperty("hourly")
    public List<Hourly> hourly = null;
    @JsonProperty("daily")
    public List<Daily> daily = null;

}
