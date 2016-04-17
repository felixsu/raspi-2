package com.felix.raspi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by fsoewito on 4/17/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private String timezone;
    private Integer offset;
    @JsonProperty("currently")
    private CurrentWeather currentWeather;

    public Weather() {
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentWeather {
        private Long time;
        private String summary;
        private String icon;
        private Double precipIntensity;
        private Double precipProbability;
        private Double temperature;
        private Double apparentTemperature;
        private Double dewPoint;
        private Double humidity;
        private Double cloudCover;
        private Double visibility;
        private Double pressure;
        private Double ozone;

        public CurrentWeather() {
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Double getPrecipIntensity() {
            return precipIntensity;
        }

        public void setPrecipIntensity(Double precipIntensity) {
            this.precipIntensity = precipIntensity;
        }

        public Double getPrecipProbability() {
            return precipProbability;
        }

        public void setPrecipProbability(Double precipProbability) {
            this.precipProbability = precipProbability;
        }

        public Double getTemperature() {
            return temperature;
        }

        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }

        public Double getApparentTemperature() {
            return apparentTemperature;
        }

        public void setApparentTemperature(Double apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }

        public Double getDewPoint() {
            return dewPoint;
        }

        public void setDewPoint(Double dewPoint) {
            this.dewPoint = dewPoint;
        }

        public Double getHumidity() {
            return humidity;
        }

        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }

        public Double getCloudCover() {
            return cloudCover;
        }

        public void setCloudCover(Double cloudCover) {
            this.cloudCover = cloudCover;
        }

        public Double getVisibility() {
            return visibility;
        }

        public void setVisibility(Double visibility) {
            this.visibility = visibility;
        }

        public Double getPressure() {
            return pressure;
        }

        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }

        public Double getOzone() {
            return ozone;
        }

        public void setOzone(Double ozone) {
            this.ozone = ozone;
        }
    }
}
