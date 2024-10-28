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
    private Long costId;

    @ManyToOne
    @JoinColumn(name = "maintenanceId")
    private Maintenance maintenance;

    @Column(name = "value")
    private float value;

    @Column(name = "description")
    private String description;
}
