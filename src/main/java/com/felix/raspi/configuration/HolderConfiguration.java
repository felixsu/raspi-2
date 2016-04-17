package com.felix.raspi.configuration;

import com.felix.raspi.model.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fsoewito on 3/31/2016.
 */

@Configuration
public class HolderConfiguration {

    @Bean(name = "temperatureHolder")
    public ConcurrentMap<String, Integer> temperatureValueMap(){
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "weatherHolder")
    public ConcurrentMap<String, Weather> weatherHolder(){
        return new ConcurrentHashMap<>();
    }
}
