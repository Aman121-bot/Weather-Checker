package com.weather.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.Model.WeatherSummary;
import com.weather.service.WeatherService;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/daily-summary")
    public List<WeatherSummary> getDailySummary(@RequestParam String city) {
        return weatherService.getDailySummary(city);
    }
}



