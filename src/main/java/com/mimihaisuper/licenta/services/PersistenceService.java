package com.mimihaisuper.licenta.services;

import com.mimihaisuper.licenta.models.AcquisitionModule;
import com.mimihaisuper.licenta.models.Measurement;
import com.mimihaisuper.licenta.models.Sensor;
import com.mimihaisuper.licenta.repository.AcquisitionModuleRepository;
import com.mimihaisuper.licenta.repository.MeasurementRepository;
import com.mimihaisuper.licenta.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersistenceService {
    Logger logger = LoggerFactory.getLogger(PersistenceService.class);

    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AcquisitionModuleRepository moduleRepository;

    public void postSensorMessage(String moduleUUID, String sensorType, String value) {
        long startTime = System.currentTimeMillis();

        AcquisitionModule acquisitionModule = getModule(moduleUUID);
        Sensor sensor = getSensor(sensorType, acquisitionModule);
        Measurement measurement = new Measurement();
        measurement.setCreationDate(new Date());
        measurement.setValue(value);
        sensor.setAcquisitionModule(acquisitionModule);
        measurement.setSensor(sensor);
        moduleRepository.save(acquisitionModule);
        sensorRepository.save(sensor);
        measurementRepository.save(measurement);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        logger.info("message " + value + " posted in " + elapsedTime + " milliseconds!");
    }

    private Sensor getSensor(String sensorType, AcquisitionModule acquisitionModule) {
        Sensor sensor = new Sensor();
        if (!sensorRepository.existsBySensorTypeAndAcquisitionModule(sensorType, acquisitionModule)) {
            sensor.setSensorType(sensorType);
            sensor = sensorRepository.save(sensor);
        } else {
            sensor = sensorRepository.findBySensorTypeAndAcquisitionModule(sensorType, acquisitionModule);
        }
        return sensor;
    }

    private AcquisitionModule getModule(String moduleUUID) {
        AcquisitionModule acquisitionModule = new AcquisitionModule();
        if (!moduleRepository.existsByUuid(moduleUUID)) {
            acquisitionModule.setUuid(moduleUUID);
            acquisitionModule = moduleRepository.save(acquisitionModule);
        } else {
            acquisitionModule = moduleRepository.findByUuid(moduleUUID);
        }
        return acquisitionModule;
    }
}
