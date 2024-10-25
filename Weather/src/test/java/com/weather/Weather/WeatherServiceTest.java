package com.weather.Weather;

import com.weather.Model.WeatherData;
import com.weather.Repository.WeatherRepository;
import com.weather.service.WeatherService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private WeatherRepository weatherRepository;

    @Test
    public void testFetchWeatherData() {
        // Given: Create a mock WeatherData object
        WeatherData mockData = new WeatherData("Delhi", "Clear", 300.0, 295.0, LocalDateTime.now());

        // When: Mock the save method to return the mock object
        Mockito.when(weatherRepository.save(Mockito.any(WeatherData.class)));

        // Call the method to be tested
        weatherService.fetchWeatherData();

        // Then: Verify that the save method was called once
        Mockito.verify(weatherRepository, Mockito.times(1)).save(Mockito.any(WeatherData.class));
    }
}
