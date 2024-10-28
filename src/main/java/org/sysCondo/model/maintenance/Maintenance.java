package org.sysCondo.model.maintenance;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.cost.Cost;
import org.sysCondo.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "maintenance")
    private Set<Cost> costSet = new HashSet<>();

    @Column(name = "status")
    private String status;
}
