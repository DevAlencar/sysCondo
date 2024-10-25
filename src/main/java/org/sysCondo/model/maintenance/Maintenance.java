package org.sysCondo.model.maintenance;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.cost.Cost;
import org.sysCondo.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "maintenance")
@Getter
@Setter
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintenanceId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userMaintenanceFk;

    @ManyToOne
    @JoinColumn(name = "commonAreaId")
    private CommonArea commonAreaMaintenanceFk;

    @ManyToOne
    @JoinColumn(name = "costId")
    private Cost costMaintenanceFk;

    @Column(name = "status")
    private String status;
}
