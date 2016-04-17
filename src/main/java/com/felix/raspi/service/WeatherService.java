package com.felix.raspi.service;

import com.felix.raspi.model.Weather;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fsoewito on 4/17/2016.
 */

@Service
public class WeatherService {

    @Resource(name = "weatherHolder")
    private ConcurrentMap<String, Weather> weatherHolder;

    @Scheduled
    private void scanWeather(){
        
    }

}
