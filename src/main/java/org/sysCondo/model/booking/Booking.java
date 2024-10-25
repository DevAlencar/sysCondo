package org.sysCondo.model.booking;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userBookingFk;

    @ManyToOne
    @JoinColumn(name = "commonAreaId")
    private CommonArea commonAreaBookingFk;

    @Column(name = "bookingDate")
    private LocalDate bookingDate;
}
