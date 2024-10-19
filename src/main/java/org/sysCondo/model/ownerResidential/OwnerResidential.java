package org.sysCondo.model.ownerResidential;
import org.sysCondo.model.unitResidential.UnitResidential;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owner_residential")
public class OwnerResidential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long owner_id;

    @Column(name = "name")
    private String name;

    @Column(name = "document")
    private String document;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "ownerResidential", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UnitResidential> unitsResidentials;

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public List<UnitResidential> getUnitsResidentials() {
        return unitsResidentials;
    }

    public void setUnitsResidentials(List<UnitResidential> unitsResowner_identials) {
        this.unitsResidentials = unitsResowner_identials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
