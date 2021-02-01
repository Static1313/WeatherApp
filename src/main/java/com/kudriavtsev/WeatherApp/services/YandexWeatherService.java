package com.kudriavtsev.WeatherApp.services;


import com.kudriavtsev.WeatherApp.model.Weather;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YandexWeatherService implements WeatherInterface {

    private String cityName;

    public YandexWeatherService(@Value("${app.city-name}") String cityName) {
        this.cityName = cityName;
    }


    @Override
    @SneakyThrows
    public List<Weather> gWeather() {
        Document doc = Jsoup.connect(String.format("https://yandex.ru/pogoda/%s", cityName)).get();
        Element tempValue = doc.selectFirst(".temp_value");

        return List.of(new Weather("YandexWeather", cityName, tempValue.text()));
    }
}
