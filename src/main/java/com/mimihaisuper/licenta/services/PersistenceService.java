package com.mimihaisuper.licenta.services;

import com.mimihaisuper.licenta.models.Sensor;
import com.mimihaisuper.licenta.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersistenceService {

    @Autowired
    SensorRepository sensorRepository;

    public void postSensorMessage(String value, String sensorType) {
        Sensor sensor = new Sensor();
        sensor.setDate(new Date());
        sensor.setValue(value);
        sensor.setSensorType(sensorType);
        sensorRepository.save(sensor);
        System.out.println("message " + value + " posted!");
    }
}
