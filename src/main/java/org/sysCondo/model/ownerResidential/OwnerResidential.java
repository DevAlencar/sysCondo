package org.sysCondo.model.ownerResidential;
import org.sysCondo.model.unitResidential.UnitResidential;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ownerResidential")
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerDocument() {
        return ownerDocument;
    }

    public void setOwnerDocument(String ownerDocument) {
        this.ownerDocument = ownerDocument;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public List<UnitResidential> getUnitsResidentials() {
        return unitsResidentials;
    }

    public void setUnitsResidentials(List<UnitResidential> unitsResidentials) {
        this.unitsResidentials = unitsResidentials;
    }
}
