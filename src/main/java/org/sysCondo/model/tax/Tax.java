package org.sysCondo.model.tax;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.user.User;
import org.sysCondo.model.userTaxPayed.UserTaxPayed;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "fkTax")
    private Set<UserTaxPayed> taxPayedSet;


}
