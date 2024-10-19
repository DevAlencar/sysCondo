package org.sysCondo;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.sysCondo.controller.UnitResidentialController;
import org.sysCondo.controller.UserController;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.ownerResidential.OwnerResidential;
import org.sysCondo.model.user.UserRole;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        //UserController userController = new UserController();
        //userController.createUser("Ricardo", "(12)934567890", "123456789", UserRole.USER);

        UnitResidentialController unitResidentialController = new UnitResidentialController();
        OwnerResidential owner1 = new OwnerResidential();

        owner1.setName("Ricardo");
        owner1.setDocument("sbrubles");
        owner1.setOwner_id(1L);

        owner1.setUnitsResidentials(null);

        unitResidentialController.createUnitResidential(2500, owner1);
    }
}
