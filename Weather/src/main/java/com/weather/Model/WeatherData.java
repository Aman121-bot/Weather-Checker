package com.weather.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class WeatherData {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String city;
    private double temp;
    private double feelsLike;
    private String mainCondition;  // Added for storing the weather condition
    private LocalDateTime timestamp;  // Added to store the time of data collection

    public WeatherData(String city, String mainCondition, double temp, double feelsLike, LocalDateTime timestamp) {
        this.city = city;
        this.mainCondition = mainCondition;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.timestamp = timestamp;
    }
}
