package com.mimihaisuper.licenta.repository;

import com.mimihaisuper.licenta.models.Sensor;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SensorRepository extends CrudRepository<Sensor, String> {
}
