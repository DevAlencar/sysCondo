package org.sysCondo.model.cost;

import org.sysCondo.model.maintenance.Maintenance;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cost")
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

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
