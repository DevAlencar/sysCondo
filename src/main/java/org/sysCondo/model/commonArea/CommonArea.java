package org.sysCondo.model.commonArea;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commonArea")
@Getter
@Setter
public class CommonArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commonAreaId;

    @OneToMany(mappedBy = "commonAreaMaintenanceFk")
    private Set<Maintenance> maintenanceSet = new HashSet<>();

    @OneToMany(mappedBy = "commonAreaBookingFk")
    private Set<Booking> bookingSet = new HashSet<>();

    @Column(name = "commonAreaName")
    private String commonAreaName;
}
