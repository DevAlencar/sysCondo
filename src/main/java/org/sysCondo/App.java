package org.sysCondo;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.sysCondo.controller.*;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.ownerResidential.OwnerResidential;
import org.sysCondo.model.user.User;
import org.sysCondo.model.user.UserRole;
import org.sysCondo.model.vehicle.BrandEnum;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.booking.Booking;
import java.time.LocalDate;

import javax.validation.constraints.Null;

public class App {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSession();

        UserController userController = new UserController();
        userController.createUser("Ricardo", "(12)934567890", "123456789", UserRole.USER);

        //UnitResidentialController unitResidentialController = new UnitResidentialController();
        //OwnerResidential owner1 = new OwnerResidential();

        //owner1.setName("Ricardo");
        //owner1.setDocument("sbrubles");
        //owner1.setOwner_id(1L);

        //owner1.setUnitsResidentials(null);

        //unitResidentialController.createUnitResidential(2500, owner1);

        //User usuario1 = new User();
        //usuario1.setUserName("John Doe");
        //usuario1.setUserContact("123456789");
        //usuario1.setUserDocument("AB123456");
        //usuario1.setUserRole(UserRole.USER);
        //usuario1.setUserId(2L);

        //VehicleController vehicleController = new VehicleController();
        //vehicleController.createVehicle("ABC-1234", BrandEnum.VOLKSWAGEN, usuario1);

        // cria uma area comum de exemplo
        //CommonArea commonArea = new CommonArea();
        //commonArea.setCommonAreaName("Salão de festas");
        //commonArea.setCommonAreaId(2013201320132013131L);

        CommonAreaController commonAreaController = new CommonAreaController();
        //commonAreaController.createCommonArea("Churrasqueira");


        //BookingController bookingController = new BookingController();
        //bookingController.createBooking(usuario1, commonArea, LocalDate.now());

        //cria uma manutencao de exemplo
        MaintenanceController maintenanceController = new MaintenanceController();
        //maintenanceController.createMaintenance(userController.getUserById(1L), commonAreaController.getCommonAreaById(1L), "Esperando autorização");

        //criando custo
        CostController costController = new CostController();
        //costController.createCost(maintenanceController.getMaintenanceById(1L), 1999.99F, "Nova grelha 2");
        //System.out.println(costController.getAllCostsByMaintenance(maintenanceController.getMaintenanceById(1L)));

    }
}
