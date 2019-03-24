package com.mimihaisuper.licenta.repository;

import com.mimihaisuper.licenta.models.AcquisitionModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquisitionModuleRepository extends CrudRepository<AcquisitionModule, Long> {
    boolean existsByUuid(String uuid);
    AcquisitionModule findByUuid(String uuid);
}
