package org.sysCondo.model.userTaxPayed;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.tax.Tax;
import org.sysCondo.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "userTaxPayed")
@Getter
@Setter
public class UserTaxPayed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User fkUser;

    @ManyToOne
    @JoinColumn(name = "taxId", nullable = false)
    private Tax fkTax;
}
