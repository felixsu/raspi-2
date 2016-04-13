package com.felix.raspi.service;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by fsoewito on 3/30/2016.
 */

@Service
public class GpioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GpioService.class.getName());

    @Autowired
    @Qualifier(value = "indicatorLed")
    private GpioPinDigitalOutput pinLed;

    @Scheduled(cron = "${bp.indicatorLed}")
    public void indicatorLed(){
        pinLed.toggle();
    }

}
