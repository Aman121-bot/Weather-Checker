package com.weather.Exception;

public class WeatherDataException extends RuntimeException {
    public WeatherDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
