package com.kudriavtsev.WeatherApp.controller;

import com.kudriavtsev.WeatherApp.model.Weather;
import com.kudriavtsev.WeatherApp.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherRestController {

    private final WeatherService weatherService;

    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String hello() {
        return "Server is processing!!";
    }

    @GetMapping("api/weather")
    public List<Weather> getWeather() {
        return weatherService.gWeather();
    }


}
