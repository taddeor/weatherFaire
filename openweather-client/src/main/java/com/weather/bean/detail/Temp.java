
package com.weather.bean.detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "day",
    "min",
    "max",
    "night",
    "eve",
    "morn"
})
@Data
public class Temp {

    @JsonProperty("day")
    public Double day;
    @JsonProperty("min")
    public Double min;
    @JsonProperty("max")
    public Double max;
    @JsonProperty("night")
    public Double night;
    @JsonProperty("eve")
    public Double eve;
    @JsonProperty("morn")
    public Double morn;

}
