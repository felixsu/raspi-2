package com.felix.raspi.controller;

import com.felix.raspi.controller.base.GenericController;
import com.felix.raspi.model.entity.Authorities;
import com.felix.raspi.model.entity.Users;
import com.felix.raspi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fsoewito on 2/20/2016.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends GenericController<Users, Integer> {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @RequestMapping(
            value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    public List<Users> getUsers() {
        return service.findAll();
    }


    @RequestMapping(
            value = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    public Users signUp(@RequestBody Users users){
        setUserRole(users);
        service.create(users);
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
