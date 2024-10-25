package org.sysCondo.model.userResidential;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userResidential")
@Getter
@Setter
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
}
