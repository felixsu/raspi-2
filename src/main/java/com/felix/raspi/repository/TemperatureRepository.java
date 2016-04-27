package com.felix.raspi.repository;

import com.felix.raspi.model.entity.TemperatureDto;
import org.springframework.stereotype.Repository;

/**
 * Created by felix on 24/04/2016.
 */

@Repository
public interface TemperatureRepository extends GenericRepository<TemperatureDto, Integer> {

    TemperatureDto findByDeviceId(String deviceId);
}
