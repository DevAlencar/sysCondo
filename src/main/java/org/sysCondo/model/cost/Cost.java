package org.sysCondo.model.cost;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.maintenance.Maintenance;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cost")
@Getter
@Setter
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int costId;

    @OneToMany(mappedBy = "costMaintenanceFk")
    private Set<Maintenance> maintenanceSet = new HashSet<>();

    @Column(name = "value")
    private float value;

    @Column(name = "description")
    private String description;
}
