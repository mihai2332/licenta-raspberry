package com.mimihaisuper.licenta.repository;

import com.mimihaisuper.licenta.models.AcquisitionModule;
import com.mimihaisuper.licenta.models.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, String> {
    boolean existsBySensorTypeAndAcquisitionModule(String sensorType, AcquisitionModule acquisitionModule);
    Sensor findBySensorTypeAndAcquisitionModule(String sensorType, AcquisitionModule acquisitionModule);
}
