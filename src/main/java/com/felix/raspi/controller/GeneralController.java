package com.felix.raspi.controller;

import com.felix.raspi.model.Temperature;
import com.felix.raspi.model.entity.Authorities;
import com.felix.raspi.model.entity.Users;
import com.felix.raspi.service.TemperatureService;
import com.felix.raspi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fsoewito on 3/28/2016.
 */

@Controller
public class GeneralController {
    @Autowired
    private UserService userService;

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView("welcome");
        Temperature temperature = temperatureService.readTemperature("default");

        extractTemperature(mv, temperature);
        return mv;
    }


    private void extractTemperature(ModelAndView mv, Temperature t){

        mv.addObject("probeId", t.getDeviceId());
        mv.addObject("temperature", String.valueOf(t.getTemperature()));
    }

    @RequestMapping(
            value = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    public Users signUp(@RequestBody Users users){
        setUserRole(users);
        userService.create(users);
        return users;
    }

    private void setUserRole(Users users){
        Set<Authorities> roles = new HashSet<>();
        roles.add(new Authorities(Authorities.ROLE_USER));
        if (users != null) {
            users.setRoles(roles);
        }
    }
}
