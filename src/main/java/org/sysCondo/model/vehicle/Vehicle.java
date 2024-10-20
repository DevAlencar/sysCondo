package org.sysCondo.model.vehicle;

import org.sysCondo.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(name = "vehicleNumber", nullable = false)
    private String vehicleNumber;

    @Enumerated(EnumType.STRING) // Armazena o ENUM como uma string no banco de dados
    @Column(name = "vehicleBrand", nullable = false)
    private BrandEnum vehicleBrand;

    @ManyToOne // Define a relação de muitos-para-um com a entidade User
    @JoinColumn(name = "userId", nullable = false) // Coluna que armazena a chave estrangeira
    private User userVehicleFk;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public BrandEnum getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(BrandEnum vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public User getUserVehicleFk() {
        return userVehicleFk;
    }

    public void setUserVehicleFk(User userVehicleFk) {
        this.userVehicleFk = userVehicleFk;
    }
}
