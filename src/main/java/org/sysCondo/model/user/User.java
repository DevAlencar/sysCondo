package org.sysCondo.model.user;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.tax.Tax;
import org.sysCondo.model.vehicle.Vehicle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "userVehicleFk", fetch = FetchType.LAZY)
    private List<Vehicle> vehicleList = new ArrayList<>();

    @OneToMany(mappedBy = "userTaxFk", fetch = FetchType.LAZY)
    private Set<Tax> taxSet = new HashSet<>();

    @OneToMany(mappedBy = "userMaintenanceFk")
    private Set<Maintenance> maintenanceSet = new HashSet<>();

    @OneToMany(mappedBy = "userBookingFk")
    private Set<Booking> bookingSet = new HashSet<>();

    // unitResidentialFk
    @Column(name = "unitResidentialFk")
    private String unitResidentialFk;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "userContact", nullable = false)
    private String userContact;

    @Column(name = "userDocument", nullable = false, unique = true)
    private String userDocument;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false)
    private UserRole userRole;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;
}
