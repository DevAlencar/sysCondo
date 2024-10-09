package org.sysCondo.models.unitResidential;

import org.sysCondo.models.ownerResidential.OwnerResidential;

import javax.persistence.*;

@Entity
@Table(name = "unit_residential")
public class UnitResidential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unit_id;

    @Column(name = "size")
    private float size;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerResidential ownerResidential;

    public int getNumber() {
        return unit_id;
    }

    public void setNumber(int unit_id) {
        this.unit_id = unit_id;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public OwnerResidential getOwnerResidential() {
        return ownerResidential;
    }

    public void setOwnerResidential(OwnerResidential ownerResidential) {
        this.ownerResidential = ownerResidential;
    }
}
