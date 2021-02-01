package com.kudriavtsev.WeatherApp.services;


import com.kudriavtsev.WeatherApp.model.Weather;
import com.kudriavtsev.WeatherApp.services.dto.OpenWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenWeatherService implements WeatherService{

    private final RestTemplate restTemplate;

    @Value("${app.openweather-api-key}")
    private String apiKey;

    @Value("${app.city-name}")
    private String cityName;


    @Override
    public List<Weather> gWeather() {
        final String url = String.format(
                "https://api.openweathermap.org/data/2.5/" +
                    "weather?q=%s&units=metric&lang=ru&appid=%s",
                cityName, apiKey);

        OpenWeatherDto dto = restTemplate.getForObject(url, OpenWeatherDto.class);

        return Collections.singletonList(toModel(dto));
    }
    private Weather toModel(OpenWeatherDto dto) {
        return new Weather(
                "OpenWeather", cityName, dto.getMain().getTemperature()
        );
    }
}
