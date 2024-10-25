package com.weather.Utility;

import com.weather.Model.WeatherData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WeatherUtility {

    public WeatherData convertResponseToWeatherData(Map<String, Object> response, String city) {
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        List<?> weatherList = (List<?>) response.get("weather");

        if (main == null || weatherList == null || weatherList.isEmpty()) {
            throw new IllegalArgumentException("Invalid response structure");
        }

        String weatherCondition = (String) ((Map<String, Object>) weatherList.get(0)).get("main");
        double temp = (double) main.get("temp");
        double feelsLike = (double) main.get("feels_like");
        long timestamp = ((Number) response.get("dt")).longValue();

        return new WeatherData(city, weatherCondition, temp, feelsLike, LocalDateTime.ofEpochSecond(timestamp, 0, java.time.ZoneOffset.UTC));
    }

    public String getDominantWeatherCondition(List<WeatherData> dataList) {
        return dataList.stream()
                .collect(Collectors.groupingBy(WeatherData::getMainCondition, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Clear");
    }
}
