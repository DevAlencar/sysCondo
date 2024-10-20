package org.sysCondo.model.user;

import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.tax.Tax;
import org.sysCondo.model.vehicle.Vehicle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "userVehicleFk", fetch = FetchType.LAZY)
    private Set<Vehicle> vehicleSet = new HashSet<>();

    @OneToMany(mappedBy = "userTaxFk", fetch = FetchType.LAZY)
    private Set<Tax> taxSet = new HashSet<>();

    @OneToMany(mappedBy = "userMaintenanceFk")
    private Set<Maintenance> maintenanceSet = new HashSet<>();

    @OneToMany(mappedBy = "userBookingFk")
    private Set<Booking> bookingSet = new HashSet<>();

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "userContact", nullable = false)
    private String userContact;

    @Column(name = "userDocument", nullable = false, unique = true)
    private String userDocument;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false)
    private UserRole userRole;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Vehicle> getVehicleSet() {
        return vehicleSet;
    }

    public void setVehicleSet(Set<Vehicle> vehicleSet) {
        this.vehicleSet = vehicleSet;
    }

    public Set<Tax> getTaxSet() {
        return taxSet;
    }

    public void setTaxSet(Set<Tax> taxSet) {
        this.taxSet = taxSet;
    }

    public Set<Maintenance> getMaintenanceSet() {
        return maintenanceSet;
    }

    public void setMaintenanceSet(Set<Maintenance> maintenanceSet) {
        this.maintenanceSet = maintenanceSet;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
