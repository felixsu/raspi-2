package com.felix.raspi.model;

/**
 * Created by felix on 23/04/2016.
 */
public class Temperature {
    private String deviceId;
    private Double temperature;
    private Double apparentTemperature;
    private TemperatureUnit unit;
    private String name;

    public Temperature(String deviceId, Double temperature, Double apparentTemperature, TemperatureUnit unit, String name) {
        this.deviceId = deviceId;
        this.temperature = temperature;
        this.apparentTemperature = temperature;
        this.unit = unit;
        this.name = name;
    }

    public Temperature(String deviceId, Double temperature, Double apparentTemperature, String name) {
        this(deviceId, temperature, apparentTemperature, TemperatureUnit.CELSIUS, name);
    }

    public Temperature(String deviceId, int temperature, int apparentTemperature, String name) {
        Integer currTemp = temperature /100;
        Double dCurrTemp= currTemp.doubleValue()/10;

        Integer appaTemp = apparentTemperature/100;
        Double dAppaTemp= appaTemp.doubleValue()/10;

        this.deviceId = deviceId;
        this.temperature = dCurrTemp;
        this.apparentTemperature = dAppaTemp;
        this.unit = TemperatureUnit.CELSIUS;
        this.name = name;
    }

    public Temperature() {
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public TemperatureUnit getUnit() {
        return unit;
    }

    public void setUnit(TemperatureUnit unit) {
        this.unit = unit;
    }

    public enum TemperatureUnit{
        CELSIUS,
        FAHRENHEIT,
        KELVIN
    }
}
