package com.felix.raspi.controller;

import com.felix.raspi.model.Temperature;
import com.felix.raspi.model.entity.Authorities;
import com.felix.raspi.model.entity.Users;
import com.felix.raspi.service.GeneralService;
import com.felix.raspi.service.UserService;
import com.felix.raspi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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
    private GeneralService generalService;

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView("welcome");
        Temperature[] temperatures = generalService.readTemperature();
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
        return generalService.readTemperature();
    }


    private void extractTemperature(ModelAndView mv, Temperature t){
        Double tempDouble = t.getTemp().doubleValue()/1000;

        mv.addObject("probeId", t.getId());
        mv.addObject("temperature", String.valueOf(tempDouble));
        mv.addObject("time", DateUtil.dateAsString(DateUtil.DateFormat.COMMON, t.getTime()));
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
