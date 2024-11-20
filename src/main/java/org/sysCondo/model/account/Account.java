package org.sysCondo.model.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "value")
    private float value;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "finishDate")
    private LocalDate finishDate;

}
