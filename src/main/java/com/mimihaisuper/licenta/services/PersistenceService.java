package com.mimihaisuper.licenta.services;

import com.mimihaisuper.licenta.models.SensorMQ135;
import com.mimihaisuper.licenta.repository.SensorMQ135Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersistenceService {

    @Autowired
    SensorMQ135Repository sensorMQ135Repository;

    public void postMQ135Message(String value) {
        SensorMQ135 sensorMQ135 = new SensorMQ135();
        sensorMQ135.setDate(new Date());
        sensorMQ135.setValue(value);
        sensorMQ135Repository.save(sensorMQ135);
    }
}
