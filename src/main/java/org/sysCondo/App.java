package org.sysCondo;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.sysCondo.controller.UnitResidentialController;
import org.sysCondo.controller.UserController;
import org.sysCondo.controller.VehicleController;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.ownerResidential.OwnerResidential;
import org.sysCondo.model.user.User;
import org.sysCondo.model.user.UserRole;
import org.sysCondo.model.vehicle.BrandEnum;

import javax.validation.constraints.Null;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        //UserController userController = new UserController();
        //userController.createUser("Ricardo", "(12)934567890", "123456789", UserRole.USER);

        //UnitResidentialController unitResidentialController = new UnitResidentialController();
        //OwnerResidential owner1 = new OwnerResidential();

        //owner1.setName("Ricardo");
        //owner1.setDocument("sbrubles");
        //owner1.setOwner_id(1L);

        //owner1.setUnitsResidentials(null);

        //unitResidentialController.createUnitResidential(2500, owner1);

        User usuario1 = new User();
        usuario1.setName("John Doe");
        usuario1.setContact("123456789");
        usuario1.setDocument("AB123456");
        usuario1.setRole(UserRole.USER);
        usuario1.setId(2L);

        VehicleController vehicleController = new VehicleController();
        vehicleController.createVehicle("ABC-1234", BrandEnum.VOLKSWAGEN, usuario1);

    }
}
