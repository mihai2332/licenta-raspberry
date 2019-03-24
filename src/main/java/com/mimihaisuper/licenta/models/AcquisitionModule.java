package com.mimihaisuper.licenta.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ACQUISITION_MODULE")
public class AcquisitionModule {
    @Id
    @GeneratedValue
    @Column(name = "MODULE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UUID")
    private String uuid;

    @OneToMany(mappedBy = "acquisitionModule")
    private Set<Sensor> sensors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }
}
