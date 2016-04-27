package com.felix.raspi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by fsoewito on 4/13/2016.
 */
public class RaspiServletListener implements ServletContextListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(RaspiServletListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        LOGGER.info("context initialized on ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("context destroy on ");
    }
}
