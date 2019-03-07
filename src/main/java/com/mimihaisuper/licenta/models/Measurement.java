package com.mimihaisuper.licenta.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "MEASUREMENT")
public class Measurement {

    @Id
    @Column(name = "MEASUREMENT_ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne
    @JoinColumn(name = "SENSOR_ID")
    private Sensor sensor;
}
