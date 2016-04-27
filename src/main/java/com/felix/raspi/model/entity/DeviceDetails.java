package com.felix.raspi.model.entity;

import com.felix.raspi.constants.TableConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by felix on 24/04/2016.
 */

@Entity
@Table(name = TableConstant.TABLE_DEVICE_DETAILS)
public class DeviceDetails implements Serializable{

    @Id
    private String id;

    @Column(name = "device_name")
    private String name;

    @Column(name = "description")
    private String description;

    public DeviceDetails() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
