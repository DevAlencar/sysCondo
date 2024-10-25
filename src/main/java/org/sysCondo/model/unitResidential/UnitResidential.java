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
}
