package com.kudriavtsev.WeatherApp.services;


import com.kudriavtsev.WeatherApp.model.Weather;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class YandexWeatherService implements WeatherService {

    @Value("${app.city-name}")
    private String cityName;


    @Override
    @SneakyThrows
    public List<Weather> gWeather() {

        Document doc = Jsoup.connect(String.format("https://yandex.ru/pogoda/%s", cityName)).get();
        Element tempValue = doc.selectFirst(".temp__value");

        return List.of(new Weather("YandexWeather", cityName, tempValue.text()));
    }
}
