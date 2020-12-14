package com.weather.service.impl;

import com.weather.api.ForecastWeatherApi;
import com.weather.bean.prediction.ListValue;
import com.weather.response.WeatherOutWorkHour;
import com.weather.response.WeatherWorkHour;
import com.weather.service.WeatherAggregator;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractAggregator<T> implements WeatherAggregator<T> {
    final ForecastWeatherApi forecastWeatherApi;

    /**
     * call service open weather in forecast mode and extract next three days
     * @param city
     * @return
     */
    @Override
    public T callServiceWeather(String city) {

        var start = LocalDateTime.now() .plusDays(1).toLocalDate().atTime(LocalTime.MIDNIGHT);
        var end = start.plus(3, ChronoUnit.DAYS);

        //call service forecast for prediction by city
        var prediction = forecastWeatherApi.predictionCityWeather(city);

        prediction.getList().sort(Comparator.comparing(ListValue::getDateTime));
        //filtering and Take next three days
        var nextTreDays = prediction.getList().stream().filter(listValue -> listValue.getDateTime().isAfter(start) && listValue.getDateTime().isBefore(end)).collect(Collectors.toList());

        //grouping by date and sort from min to max date
        var dateListLinkedHashMap = nextTreDays
                .stream()
                .collect(Collectors.groupingBy(ListValue::getDate))
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, HashMap::new));

        return prediction(dateListLinkedHashMap);
    }

    /**
     * abstract methode to filter future prediction of service openweather
     * @param localDateListMap
     * @return
     */
    public abstract T prediction(Map<LocalDate, List<ListValue>> localDateListMap);

    /**
     * calculating prediction for work hour and remove the entry to find
     * @param end
     * @param startWork
     * @param localDateListEntry
     * @return
     */
    protected List<WeatherWorkHour> calculatePredictionWorkHours(LocalDateTime end, LocalDateTime startWork, List<ListValue> localDateListEntry) {
        //filtered between star and end date
        var filteredValueList = localDateListEntry.stream()
                .filter(listValue -> listValue.getDateTime().isAfter(startWork) && listValue.getDateTime().isBefore(end))
                .collect(Collectors.toList());
        //remove
        localDateListEntry.removeAll(filteredValueList);
        //return mapping list on WeatherWorkHour
        return filteredValueList.stream()
                .map(listValue -> WeatherWorkHour.from(listValue.getDateTime(),listValue.getMain().getTempMax(), listValue.getMain().getTempMin(), listValue.getMain().getHumidity()))
                .collect(Collectors.toList());

    }

    /**
     * all antry with out calculating workHour
     * @param end
     * @param startWork
     * @param localDateListEntry
     * @return
     */
    protected List<WeatherOutWorkHour> calculatePredictionOutSideWorkHours(LocalDateTime end, LocalDateTime startWork, List<ListValue> localDateListEntry) {
         return localDateListEntry.stream()
                .map(listValue ->   WeatherOutWorkHour.from(listValue.getDateTime(),listValue.getMain().getTempMax(), listValue.getMain().getTempMin(), listValue.getMain().getHumidity()))
                .collect(Collectors.toList());
    }
}
