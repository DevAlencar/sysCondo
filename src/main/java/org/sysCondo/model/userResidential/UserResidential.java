package org.sysCondo.model.userResidential;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userResidential")
public class UserResidential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userResidentialId;

    @Column(name = "rentDate", nullable = false)
    @Temporal(TemporalType.DATE) // Define que o tipo de dados é uma data
    private Date rentDate;

    @Column(name = "finishDate")
    @Temporal(TemporalType.DATE) // Define que o tipo de dados é uma data
    private Date finishDate;

    public Long getUserResidentialId() {
        return userResidentialId;
    }

    public void setUserResidentialId(Long userResidentialId) {
        this.userResidentialId = userResidentialId;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
