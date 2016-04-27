package com.felix.raspi.model.entity;

import com.felix.raspi.constants.TableConstant;
import com.felix.raspi.model.Temperature;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by felix on 24/04/2016.
 */

@Entity
@Table(name = TableConstant.TABLE_TEMPERATURE)
public class TemperatureDto implements Serializable {

    @Id
    @SequenceGenerator(name = TableConstant.SEQ_TEMPERATURES, sequenceName = TableConstant.SEQ_TEMPERATURES)
    @GeneratedValue(generator = TableConstant.SEQ_TEMPERATURES)
    @Column(name = TableConstant.COL_ID)
    private Integer id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "device_name")
    private String name;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "apparent_temperature")
    private Double apparentTemperature;

    @Column(name = "date")
    private Date date;

    public TemperatureDto() {
    }

    public TemperatureDto(Temperature temperature) {
        this.deviceId = temperature.getDeviceId();
        this.name = temperature.getName();
        this.temperature = temperature.getTemperature();
        this.apparentTemperature = temperature.getApparentTemperature();
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
