package com.kudriavtsev.WeatherApp.services;

import com.kudriavtsev.WeatherApp.model.Weather;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherAggregationService implements WeatherService{

    private List<WeatherService> weatherServices;

    public WeatherAggregationService(List<WeatherService> weatherServices) {
        this.weatherServices = weatherServices;
    }


    @Override
    public List<Weather> gWeather() {
        List<Weather> weatherList = new ArrayList<>();
        weatherServices.forEach(weatherService -> weatherList.addAll(weatherService.gWeather()));

        return weatherList;
    }
}
