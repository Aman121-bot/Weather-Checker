package com.weather.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.weather.Exception.ApiException;
import com.weather.Exception.WeatherDataException;
import com.weather.Model.WeatherData;
import com.weather.Model.WeatherSummary;
import com.weather.Repository.WeatherRepository;
import com.weather.Repository.WeatherSummaryRepository;
import com.weather.Utility.WeatherUtility;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherSummaryRepository summaryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherUtility weatherUtils;

    private final String openWeatherApiUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final String apiKey = System.getenv("53cdc65c6a99e773b2e77ab941a956ca"); // Use environment variable

    @Scheduled(fixedRateString = "${openweather.api.interval} * 60000")
    public void fetchWeatherData() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};

        for (String city : cities) {
            try {
                String url = String.format("%s?q=%s&appid=%s", openWeatherApiUrl, city, apiKey);
                Map<String, Object> response = restTemplate.getForObject(url, Map.class);

                if (response != null) {
                    WeatherData weatherData = weatherUtils.convertResponseToWeatherData(response, city);
                    processWeatherData(weatherData);
                } else {
                    logger.warn("Null response received from API for city: {}", city);
                }
            } catch (HttpClientErrorException e) {
                logger.error("HTTP error fetching weather data for city {}: {}", city, e.getStatusCode());
                throw new ApiException("Failed to fetch data for city: " + city, e);
            } catch (Exception e) {
                logger.error("Error fetching weather data for city {}: {}", city, e.getMessage());
                throw new WeatherDataException("Error processing weather data for city: " + city, e);
            }
        }
    }

    public void processWeatherData(WeatherData data) {
        if (data != null) {
            data.setTemp(data.getTemp() - 273.15);
            data.setFeelsLike(data.getFeelsLike() - 273.15);
            weatherRepository.save(data);
        } else {
            logger.warn("Weather data is null and cannot be processed.");
        }
    }

    public List<WeatherSummary> getDailySummary(String city) {
        LocalDate today = LocalDate.now();
        List<WeatherSummary> summaries = summaryRepository.findByCityAndDate(city, today);

        if (summaries.isEmpty()) {
            logger.info("No summary found for city: {}", city);
        }
        return summaries;
    }

    @Scheduled(cron = "0 0 * * * ?") // This schedules daily summary generation at midnight
    public void generateDailySummary() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};

        for (String city : cities) {
            LocalDate today = LocalDate.now();
            LocalDateTime startOfDay = today.atStartOfDay();
            LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

            List<WeatherData> dailyData = weatherRepository.findByCityAndTimestampBetween(city, startOfDay, endOfDay);

            if (!dailyData.isEmpty()) {
                double avgTemp = dailyData.stream().collect(Collectors.averagingDouble(WeatherData::getTemp));
                double minTemp = dailyData.stream().mapToDouble(WeatherData::getTemp).min().orElse(0.0);
                double maxTemp = dailyData.stream().mapToDouble(WeatherData::getTemp).max().orElse(0.0);
                String dominantCondition = weatherUtils.getDominantWeatherCondition(dailyData);

                WeatherSummary summary = new WeatherSummary(city, today, avgTemp, minTemp, maxTemp, dominantCondition);
                summaryRepository.save(summary);
            } else {
                logger.info("No weather data found for city: {} on date: {}", city, today);
            }
        }
    }
}
