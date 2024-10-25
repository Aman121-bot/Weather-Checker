package com.weather.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weather.Model.WeatherSummary;

@Repository
public interface WeatherSummaryRepository extends JpaRepository<WeatherSummary, Long> {

    // Find weather summary by city and date
    List<WeatherSummary> findByCityAndDate(String city, LocalDate date);

}
