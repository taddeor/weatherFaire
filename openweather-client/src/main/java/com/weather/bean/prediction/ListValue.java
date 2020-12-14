
package com.weather.bean.prediction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "main",
    "weather",
    "clouds",
    "wind",
    "visibility",
    "pop",
    "sys",
    "dt_txt"
})
@Data
public class ListValue {

    @JsonProperty("dt")
    public Integer dt;
    @JsonProperty("main")
    public Main main;
    @JsonProperty("weather")
    public List<Weather> weather = null;
    @JsonProperty("clouds")
    public Clouds clouds;
    @JsonProperty("wind")
    public Wind wind;
    @JsonProperty("visibility")
    public Integer visibility;
    @JsonProperty("pop")
    public Integer pop;
    @JsonProperty("sys")
    public Sys sys;
    @JsonProperty("dt_txt")
    public String dtTxt;


    public LocalDateTime dateTime;

    public LocalDateTime getDateTime()  {
        try {
            var date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dtTxt);
            dateTime = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return dateTime;
        }catch (ParseException e){
            return null;
        }
    }

    public LocalDate getDate(){
        try {
            var date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dtTxt);
            LocalDate localDate = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            return localDate;
        }catch (ParseException e){
            return null;
        }
    }
}
