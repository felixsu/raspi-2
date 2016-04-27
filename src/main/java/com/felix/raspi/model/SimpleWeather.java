package com.felix.raspi.model;

/**
 * Created by felix on 24/04/2016.
 */
public class SimpleWeather {

    private String icon;
    private String description;
    private Integer chanceRain;

    public SimpleWeather() {
    }

    public SimpleWeather(String icon, String description, Integer chanceRain) {
        this.icon = icon;
        this.description = description;
        this.chanceRain = chanceRain;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getChanceRain() {
        return chanceRain;
    }

    public void setChanceRain(Integer chanceRain) {
        this.chanceRain = chanceRain;
    }
}
