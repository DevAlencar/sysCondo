package org.sysCondo.model.tax;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tax")
@Getter
@Setter
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taxId;

    @Column(name = "value")
    private float value;

    @Column(name = "status")
    private String status;

    @Column(name = "finishDate")
    private LocalDate finishDate;

    @Column(name = "name")
    private String name;

}
