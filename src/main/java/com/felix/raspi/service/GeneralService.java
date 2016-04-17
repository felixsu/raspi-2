package com.felix.raspi.service;

import com.felix.raspi.model.Temperature;
import com.felix.raspi.util.JsonUtil;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by fsoewito on 3/28/2016.
 */

@Service
public class GeneralService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralService.class.getName());
    private static final Logger TEMPERATURE_LOGGER = LoggerFactory.getLogger("temperature");

    @Resource(name = "temperatureHolder")
    public ConcurrentMap<String, Integer> temperatureHolder;

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
            int i = 0;
            for (Map.Entry<String, Integer> entry : temperatureHolder.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                Temperature t = new Temperature(key, value, new Date().getTime());
                writeTemperatureLog(t);
                if (i == 0) {
                    updateLed(value);
                }
                i++;

            }
        }
    }

    private void updateLed(int value){
        if (value > 33000) {
            highTempLed.high();
            medTempLed.low();
            lowTempLed.low();
        } else if ((value > 27000) && (value <= 33000)) {
            highTempLed.low();
            medTempLed.high();
            lowTempLed.low();
        } else {
            highTempLed.low();
            medTempLed.low();
            lowTempLed.high();
        }
    }

    private void writeTemperatureLog(Temperature t){
        try {
            TEMPERATURE_LOGGER.info(JsonUtil.toJson(t));
        } catch (IOException e){
            LOGGER.error("error ", e);
        }
    }
}
