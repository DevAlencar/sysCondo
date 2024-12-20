package org.sysCondo.model.unitResidential;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "unitResidential")
@Getter
@Setter
public class UnitResidential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitResidentialId;

    @Column(name = "number", unique = true)
    private int unitResidentialNumber;

    @Column(name = "size")
    private float unitResidentialSize;

    @Column(name = "ownerName")
    private String ownerName;

    @Column(name = "ownerContact")
    private String ownerContact;
}
