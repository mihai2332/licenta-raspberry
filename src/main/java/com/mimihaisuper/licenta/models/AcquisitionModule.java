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
@Table(name = "ACQUISITION_MODULE")
public class AcquisitionModule {
    @Id
    @Column(name = "MODULE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "acquisitionModule")
    private Set<Sensor> sensors;
}
