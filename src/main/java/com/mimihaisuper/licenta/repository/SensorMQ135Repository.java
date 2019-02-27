package com.mimihaisuper.licenta.repository;

import com.mimihaisuper.licenta.models.SensorMQ135;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SensorMQ135Repository extends CrudRepository<SensorMQ135, String> {
}
