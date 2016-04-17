package com.felix.raspi.configuration;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Created by fsoewito on 3/30/2016.
 */

@Configuration
public class LedConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(LedConfiguration.class.getName());

    @Autowired
    private GpioController gpioController;

    @Bean(name = "indicatorLed")
    public GpioPinDigitalOutput indicatorLed(){
        LOGGER.info("creating indicatorLed bean");
        GpioPinDigitalOutput pinLed = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "indicatorLed", PinState.HIGH);
        pinLed.high();
        return pinLed;
    }

    @Bean(name = "changingLed")
    public GpioPinDigitalOutput changingLed(){
        LOGGER.info("creating changingLed bean");
        GpioPinDigitalOutput pinLed = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "changingLed", PinState.HIGH);
        pinLed.high();
        return pinLed;
    }

    @Bean(name = "highTempLed")
    public GpioPinDigitalOutput highTempLed(){
        LOGGER.info("creating highTempLed bean");
        GpioPinDigitalOutput pinLed = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "highTempLed", PinState.HIGH);
        pinLed.high();
        return pinLed;
    }

    @Bean(name = "medTempLed")
    public GpioPinDigitalOutput medTempLed(){
        LOGGER.info("creating highTempLed bean");
        GpioPinDigitalOutput pinLed = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "medTempLed", PinState.HIGH);
        pinLed.high();
        return pinLed;
    }

    @Bean(name = "lowTempLed")
    public GpioPinDigitalOutput lowTempLed(){
        LOGGER.info("creating lowTempLed bean");
        GpioPinDigitalOutput pinLed = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "lowTempLed", PinState.HIGH);
        pinLed.high();
        return pinLed;
    }

    @PreDestroy
    public void destroyLed(){
        LOGGER.info("entering on destroy LED bean");
        gpioController.shutdown();
    }
}
