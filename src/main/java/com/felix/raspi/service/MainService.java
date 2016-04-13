package com.felix.raspi.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by fsoewito on 2/23/2016.
 */

@Service
public class MainService {

    public String hello(Model model, String name) {
        if (name == null || name.isEmpty()) {
            name = "anonymous";
        }
        model.addAttribute("name", name);
        return "hello";
    }
}