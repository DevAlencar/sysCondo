package org.sysCondo.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(name = "vehicleNumber", nullable = false)
    private String vehicleNumber;

    // @Enumerated(EnumType.STRING) // Armazena o ENUM como uma string no banco de dados
    @Column(name = "vehicleBrand", nullable = false)
    private String vehicleBrand;
    //private BrandEnum vehicleBrand;


    @ManyToOne // Define a relação de muitos-para-um com a entidade User
    @JoinColumn(name = "userId", nullable = false) // Coluna que armazena a chave estrangeira
    private User userVehicleFk;


}
