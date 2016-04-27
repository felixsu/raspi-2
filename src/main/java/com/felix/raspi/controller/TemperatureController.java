package com.felix.raspi.controller;

import com.felix.raspi.model.Temperature;
import com.felix.raspi.service.TemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fsoewito on 4/17/2016.
 */

@Controller
@RequestMapping("/temperature")
public class TemperatureController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureController.class.getName());

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(
            value = "/hello",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @RequestMapping(
            value = "/readTemperature/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Temperature getIndoorTemperature(@PathVariable(value = "id") String id){
        LOGGER.info("calling controller with id " + id);
        return temperatureService.readTemperature(id);
    }

    @RequestMapping(
            value = "/readBean",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String[] getTemperatureBeans(){
        return temperatureService.readBean();
    }
}
