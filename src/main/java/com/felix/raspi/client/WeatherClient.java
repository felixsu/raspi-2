package com.felix.raspi.client;

import com.felix.raspi.configuration.WeatherConfiguration;
import com.felix.raspi.model.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fsoewito on 4/17/2016.
 */

@Component
public class WeatherClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class.getName());

    @Autowired
    private WeatherConfiguration weatherConfiguration;

    public WeatherForecast getCurrentWeather() {
        String targetUrl = "https://api.forecast.io/forecast/" +
                weatherConfiguration.getApiKey() +
                "/" +
                weatherConfiguration.getLatitude() +
                "," + weatherConfiguration.getLongitude() +
                "?" +
                weatherConfiguration.getParam();
        LOGGER.info("targetUrl : " + targetUrl);
        RestTemplate restTemplate = new RestTemplate();
        WeatherForecast result;
        try {
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setConnectTimeout(5*1000);
            simpleClientHttpRequestFactory.setReadTimeout(5*1000);

            restTemplate.setRequestFactory(simpleClientHttpRequestFactory);

            result = restTemplate.getForObject(targetUrl, WeatherForecast.class);
        } catch (RestClientException e) {
            LOGGER.error("error call forecast.io API", e);
            result = new WeatherForecast();
            result.setCurrentWeather(new WeatherForecast.CurrentWeather());
        }
        return result;
    }
}
