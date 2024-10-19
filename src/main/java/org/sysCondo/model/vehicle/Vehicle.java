package org.sysCondo.model.vehicle;

import org.sysCondo.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicle_id;

    @Column(name = "number", nullable = false)
    private String number;

    @Enumerated(EnumType.STRING) // Armazena o ENUM como uma string no banco de dados
    @Column(name = "brand", nullable = false)
    private BrandEnum brand;

    @ManyToOne // Define a relação de muitos-para-um com a entidade User
    @JoinColumn(name = "fk_user", nullable = false) // Coluna que armazena a chave estrangeira
    private User fkUser;

    public Long getVehicleId() {
        return vehicle_id;
    }

    public void setVehicleId(Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BrandEnum getBrand() {
        return brand;
    }

    public void setBrand(BrandEnum brand) {
        this.brand = brand;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }
}
