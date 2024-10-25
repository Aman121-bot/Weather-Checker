package com.weather.Model;

import java.time.LocalDate;

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
public class WeatherSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    private String city;
    private LocalDate date;
    private double avgTemp;
    private double minTemp;
    private double maxTemp;
    private String dominantWeatherCondition;

    public WeatherSummary(String city, LocalDate date, double avgTemp, double minTemp, double maxTemp, String dominantWeatherCondition) {
        this.city = city;
        this.date = date;
        this.avgTemp = avgTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.dominantWeatherCondition = dominantWeatherCondition;
    }
    
}
