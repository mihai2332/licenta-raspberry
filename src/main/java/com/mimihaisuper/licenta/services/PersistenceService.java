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

    public void postSensorMessage(String moduleName, String sensorType, String value) {
        AcquisitionModule acquisitionModule = getModule(moduleName);
        Sensor sensor = getSensor(sensorType);
        Measurement measurement = new Measurement();
        measurement.setCreationDate(new Date());
        measurement.setValue(value);
        sensor.setAcquisitionModule(acquisitionModule);
        measurement.setSensor(sensor);
        moduleRepository.save(acquisitionModule);
        sensorRepository.save(sensor);
        measurementRepository.save(measurement);
        logger.info("message " + value + " posted!");
    }

    private Sensor getSensor(String sensorType) {
        Sensor sensor = new Sensor();
        if (!sensorRepository.existsBySensorType(sensorType)) {
            sensor.setSensorType(sensorType);
            sensor = sensorRepository.save(sensor);
        } else {
            sensor = sensorRepository.findBySensorType(sensorType);
        }
        return sensor;
    }

    private AcquisitionModule getModule(String moduleName) {
        AcquisitionModule acquisitionModule = new AcquisitionModule();
        if (!moduleRepository.existsByName(moduleName)) {
            acquisitionModule.setName(moduleName);
            acquisitionModule = moduleRepository.save(acquisitionModule);
        } else {
            acquisitionModule = moduleRepository.findByName(moduleName);
        }
        return acquisitionModule;
    }
}
