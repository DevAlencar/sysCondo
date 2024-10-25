package org.sysCondo.model.ownerResidential;
import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.unitResidential.UnitResidential;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ownerResidential")
@Getter
@Setter
public class OwnerResidential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @Column(name = "ownerName")
    private String ownerName;

    @Column(name = "ownerDocument")
    private String ownerDocument;

    @Column(name = "ownerContact")
    private String ownerContact;

    @OneToMany(mappedBy = "ownerResidential", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UnitResidential> unitsResidentials;
}
