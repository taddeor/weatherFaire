package com.weather.service.impl;

import com.weather.api.ForecastWeatherApi;
import com.weather.bean.prediction.ListValue;
import com.weather.response.WeatherResponse;
import com.weather.response.WeatherWorkHour;
import com.weather.service.WeatherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
/**
 * specialize implementation fon retrieve a temp min and max for working hours
 */
@Slf4j
public class WeatherWorkOur extends AbstractAggregator<WeatherResponse> {
    @Value("${start.work.hour}")
    private LocalTime startWorkTime;
//    @Value("${end.work.hour}")
    private LocalTime endWorkTime = LocalTime.of(18,00);

    public WeatherWorkOur(ForecastWeatherApi forecastWeatherApi) {
        super(forecastWeatherApi);

    }

    @Override
    public WeatherType WEATHER_TYPE() {
        return WeatherType.WORKHOUR;
    }

    @Override
    public WeatherResponse prediction(Map<LocalDate, List<ListValue>> localDateListMap) {
        LocalDateTime startWork = LocalDateTime.now().plusDays(1).toLocalDate().atTime(startWorkTime).minusSeconds(1);
        LocalDateTime endWork = startWork.toLocalDate().atTime(endWorkTime).plusSeconds(1);
        List<WeatherWorkHour> workHours = new ArrayList<>();
        for (Map.Entry<LocalDate, List<ListValue>> localDateListEntry : localDateListMap.entrySet()) {
            workHours.addAll( calculatePredictionWorkHours(endWork, startWork, localDateListEntry.getValue()) );
            startWork = startWork.plusDays(1);
            endWork = endWork.plusDays(1);
        }

        return WeatherResponse.from(workHours);
    }






}
