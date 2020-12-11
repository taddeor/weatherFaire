
package com.weather.bean.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "id",
    "country",
    "sunrise",
    "sunset"
})
public class Sys {

    @JsonProperty("type")
    public Integer type;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("country")
    public String country;
    @JsonProperty("sunrise")
    public Integer sunrise;
    @JsonProperty("sunset")
    public Integer sunset;

}
