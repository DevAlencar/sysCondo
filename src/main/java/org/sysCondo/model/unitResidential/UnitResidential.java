package org.sysCondo.model.unitResidential;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.ownerResidential.OwnerResidential;

import javax.persistence.*;

@Entity
@Table(name = "unitResidential")
@Getter
@Setter
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
