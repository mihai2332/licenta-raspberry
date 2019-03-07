package com.mimihaisuper.licenta.repository;

import com.mimihaisuper.licenta.models.Measurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
}
