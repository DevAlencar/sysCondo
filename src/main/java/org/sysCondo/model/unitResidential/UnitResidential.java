package org.sysCondo.model.unitResidential;

import org.sysCondo.model.ownerResidential.OwnerResidential;

import javax.persistence.*;

@Entity
@Table(name = "unitResidential")
public class UnitResidential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitResidentialId;

    @Column(name = "size")
    private float unitResidentialSize;

    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private OwnerResidential ownerResidential;

    public int getUnitResidentialId() {
        return unitResidentialId;
    }

    public void setUnitResidentialId(int unitResidentialId) {
        this.unitResidentialId = unitResidentialId;
    }

    public float getUnitResidentialSize() {
        return unitResidentialSize;
    }

    public void setUnitResidentialSize(float unitResidentialSize) {
        this.unitResidentialSize = unitResidentialSize;
    }

    public OwnerResidential getOwnerResidential() {
        return ownerResidential;
    }

    public void setOwnerResidential(OwnerResidential ownerResidential) {
        this.ownerResidential = ownerResidential;
    }
}
