package com.felix.raspi.service;

import com.felix.raspi.model.Temperature;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by fsoewito on 3/28/2016.
 */

@Service
public class GpioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GpioService.class.getName());

    @Resource(name = "temperatureHolder")
    public ConcurrentMap<String, Temperature> temperatureHolder;

    @Autowired
    @Qualifier(value = "indicatorLed")
    private GpioPinDigitalOutput pinLed;

    @Autowired
    @Qualifier(value = "highTempLed")
    private GpioPinDigitalOutput highTempLed;

    @Autowired
    @Qualifier(value = "medTempLed")
    private GpioPinDigitalOutput medTempLed;

    @Autowired
    @Qualifier(value = "lowTempLed")
    private GpioPinDigitalOutput lowTempLed;

    @Scheduled(cron = "${bp.reportTemperature}")
    public void echo() {
        if (temperatureHolder.isEmpty()) {
            LOGGER.info("holder is empty");
        } else {
            for (Map.Entry<String, Temperature> entry : temperatureHolder.entrySet()) {
                String key = entry.getKey();
                Temperature value = entry.getValue();
                //only update use data from sensor
                if (key.contains("28")) {
                    updateLed(value.getTemperature().intValue());
                }
            }
        }
    }

    @Scheduled(cron = "${bp.indicatorLed}")
    public void indicatorLed(){
        pinLed.toggle();
    }

    private void updateLed(int value){
        if (value > 33) {
            highTempLed.high();
            medTempLed.low();
            lowTempLed.low();
        } else if ((value > 25) && (value <= 33)) {
            highTempLed.low();
            medTempLed.high();
            lowTempLed.low();
        } else {
            highTempLed.low();
            medTempLed.low();
            lowTempLed.high();
        }
    }
}
