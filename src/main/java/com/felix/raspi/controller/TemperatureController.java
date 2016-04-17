package com.felix.raspi.controller;

import com.felix.raspi.model.Temperature;
import com.felix.raspi.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fsoewito on 4/17/2016.
 */

@Controller
@RequestMapping("/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Temperature[] getAllTemperature(){
        return temperatureService.readTemperature();
    }
}
