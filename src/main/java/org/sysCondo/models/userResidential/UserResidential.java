package org.sysCondo.models.userResidential;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_residential")
public class UserResidential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_residential_id;

    @Column(name = "rent_date", nullable = false)
    @Temporal(TemporalType.DATE) // Define que o tipo de dados é uma data
    private Date rentDate;

    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE) // Define que o tipo de dados é uma data
    private Date finishDate;

    public Long getUserResidentialId() {
        return user_residential_id;
    }

    public void setUserResidentialId(Long user_residential_id) {
        this.user_residential_id = user_residential_id;
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
