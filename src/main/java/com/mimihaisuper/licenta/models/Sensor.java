package com.mimihaisuper.licenta.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "SENSOR")
public class Sensor {

    @Id
    @Column(name = "SENSOR_ID")
    private Long id;

    @Column(name = "TYPE")
    private String sensorType;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    private AcquisitionModule acquisitionModule;

    @OneToMany(mappedBy = "sensor")
    private Set<Measurement> measurements;

}
