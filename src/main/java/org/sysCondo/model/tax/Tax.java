package org.sysCondo.model.tax;

import org.sysCondo.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tax")
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taxId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userTaxFk;

    @Column(name = "value")
    private float value;

    @Column(name = "status")
    private String status;

    @Column(name = "finishDate")
    private LocalDate finishDate;

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public User getUserTaxFk() {
        return userTaxFk;
    }

    public void setUserTaxFk(User userTaxFk) {
        this.userTaxFk = userTaxFk;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getFinish_date() {
        return finishDate;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finishDate = finish_date;
    }
}
