package com.felix.raspi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by fsoewito on 4/7/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {
    private String id;
    private Integer temp;
    private Long time;

    public Temperature() {
    }

    public Temperature(String id, Integer temp, Long time) {
        this.id = id;
        this.temp = temp;
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public Integer getTemp() {
        return temp;
    }
}
