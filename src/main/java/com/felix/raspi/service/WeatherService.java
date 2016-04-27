package com.felix.raspi.service;

import com.felix.raspi.client.WeatherClient;
import com.felix.raspi.exception.ResourceNotFoundException;
import com.felix.raspi.exception.SystemNotReadyException;
import com.felix.raspi.model.SimpleWeather;
import com.felix.raspi.model.Temperature;
import com.felix.raspi.model.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fsoewito on 4/17/2016.
 */

@Service
public class WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class.getName());

    private static final String WEATHER_KEY = "weather-key";
    private static final String SIMPLE_WEATHER_KEY = "simple-weather-key";
    private static final String OUTSIDE_TEMP_ID = "f-io-src";


    @Resource(name = "weatherHolder")
    private ConcurrentMap<String, Object> weatherHolder;

    @Resource(name = "temperatureHolder")
    private ConcurrentMap<String, Temperature> temperatureHolder;


    @Autowired
    private WeatherClient weatherClient;

    @PostConstruct
    private void initWeather(){
        if (weatherHolder != null){
            scanWeather();
        } else {
            LOGGER.error("weather container not created");
        }
    }

    @Scheduled(cron = "${bp.weatherScan}")
    private void scanWeather(){
        WeatherForecast response = weatherClient.getCurrentWeather();
        weatherHolder.put(WEATHER_KEY, response);

        Temperature t;
        int currentTemperature = (int) (response.getCurrentWeather().getTemperature()*10);
        double doubleCurrentTemperature = ((double)currentTemperature)/10;
        int apparentTemperature = (int) (response.getCurrentWeather().getApparentTemperature()*10);
        double doubleApparentTemperature = ((double)apparentTemperature)/10;


        if (temperatureHolder.containsKey(OUTSIDE_TEMP_ID)){
            t = temperatureHolder.get(OUTSIDE_TEMP_ID);

            t.setTemperature(doubleCurrentTemperature);
            t.setApparentTemperature(doubleApparentTemperature);
        } else {
            t = new Temperature(OUTSIDE_TEMP_ID, doubleCurrentTemperature, doubleApparentTemperature, "forecast-io");
            temperatureHolder.put(OUTSIDE_TEMP_ID, t);
        }

        SimpleWeather sw;
        int chanceRain = (int) (response.getCurrentWeather().getPrecipProbability()*100);
        String icon = response.getCurrentWeather().getSummary();
        String summary = response.getCurrentWeather().getSummary();

        if (weatherHolder.containsKey(SIMPLE_WEATHER_KEY)) {
            sw = (SimpleWeather) weatherHolder.get(SIMPLE_WEATHER_KEY);
            sw.setChanceRain(chanceRain);
            sw.setDescription(summary);
            sw.setIcon(icon);
        } else {
            sw = new SimpleWeather(icon, summary, chanceRain);
            weatherHolder.put(SIMPLE_WEATHER_KEY, sw);
        }
    }

    public WeatherForecast readWeather(){
        WeatherForecast result;
        if (weatherHolder.isEmpty()){
            result =  new WeatherForecast();
            result.setCurrentWeather(new WeatherForecast.CurrentWeather());
        } else {
            result = (WeatherForecast) weatherHolder.get(WEATHER_KEY);
        }
        return result;
    }

    public SimpleWeather readCurrentWeather() throws SystemNotReadyException, ResourceNotFoundException{
        LOGGER.info("entering readCurrentWeather");
        if (weatherHolder.isEmpty()){
            throw new SystemNotReadyException();
        }

        if (!weatherHolder.containsKey(SIMPLE_WEATHER_KEY)){
            LOGGER.info("key not found " + SIMPLE_WEATHER_KEY);
            throw new ResourceNotFoundException();
        } else {
            return (SimpleWeather) weatherHolder.get(SIMPLE_WEATHER_KEY);
        }

    }

    public String[] readBean(){
        if (weatherHolder.isEmpty()){
            throw new SystemNotReadyException();
        } else {
            Set<String> keySet = weatherHolder.keySet();

            LOGGER.info("readBean  found " + keySet.size() + " beans");
            String[] result = new String[keySet.size()];
            int i = 0;
            for (String element : keySet){
                result[i] = element;
                i++;
            }
            return result;
        }
    }

}
