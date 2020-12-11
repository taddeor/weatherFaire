
package com.weather.bean.detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "precipitation"
})
@Data
public class Minutely {

    @JsonProperty("dt")
    public Integer dt;
    @JsonProperty("precipitation")
    public Integer precipitation;

}
