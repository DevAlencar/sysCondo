package org.sysCondo.model.maintenance;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.cost.Cost;
import org.sysCondo.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "maintenance", fetch = FetchType.EAGER)
    private List<Cost> costList = new ArrayList<>();

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    // created at
    @Column(name = "created_at") // date time
    private LocalDateTime createdAt;

    @Column(name = "refuse_reason")
    private String refuseReason;

}
