package com.felix.raspi.controller;

import com.felix.raspi.model.Temperature;
import com.felix.raspi.service.GeneralService;
import com.felix.raspi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fsoewito on 3/28/2016.
 */

@Controller
public class GeneralController {

    @Autowired
    private GeneralService service;

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView("welcome");
        Temperature[] temperatures = service.readTemperature();
        if (temperatures.length == 0){
            Temperature t = new Temperature(null, null, new Date().getTime());
            extractTemperature(mv, t);
        } else {
            extractTemperature(mv, temperatures[0]);
        }
        return mv;
    }

    @RequestMapping(
            value = "/temp",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Temperature[] getAllTemperature(){
        return service.readTemperature();
    }


    private void extractTemperature(ModelAndView mv, Temperature t){
        Double tempDouble = t.getTemp().doubleValue()/1000;

        mv.addObject("probeId", t.getId());
        mv.addObject("temperature", String.valueOf(tempDouble));
        mv.addObject("time", DateUtil.dateAsString(DateUtil.DateFormat.COMMON, t.getTime()));
    }
}
