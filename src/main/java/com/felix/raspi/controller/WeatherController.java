package com.felix.raspi.controller;

import com.felix.raspi.model.Weather;
import com.felix.raspi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by fsoewito on 4/17/2016.
 */

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Weather getWeather(){
        Weather response = weatherService.readWeather();
        response.getCurrentWeather().setTime(new Date().getTime());
        return response;
    }
}
