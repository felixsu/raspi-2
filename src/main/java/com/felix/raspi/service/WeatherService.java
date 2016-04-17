package com.felix.raspi.service;

import com.felix.raspi.client.WeatherClient;
import com.felix.raspi.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fsoewito on 4/17/2016.
 */

@Service
public class WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class.getName());

    private static final String WEATHER_KEY = "weather-key";

    @Resource(name = "weatherHolder")
    private ConcurrentMap<String, Weather> weatherHolder;

    @Autowired
    private WeatherClient weatherClient;

    @PostConstruct
    private void initWeather(){
        if (weatherHolder != null){
            Weather response = weatherClient.getCurrentWeather();
            weatherHolder.put(WEATHER_KEY, response);
        } else {
            LOGGER.error("weather container not created");
        }
    }

    @Scheduled(cron = "${bp.weatherScan}")
    private void scanWeather(){
        Weather response = weatherClient.getCurrentWeather();
        weatherHolder.put(WEATHER_KEY, response);
    }

    public Weather readWeather(){
        Weather result;
        if (weatherHolder.isEmpty()){
            result =  new Weather();
            result.setCurrentWeather(new Weather.CurrentWeather());
        } else {
            result =  weatherHolder.get(WEATHER_KEY);
        }
        return result;
    }

}
