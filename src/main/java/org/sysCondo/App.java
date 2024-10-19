package org.sysCondo;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.sysCondo.controller.UserController;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.user.UserRole;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        UserController userController = new UserController();
        userController.createUser("Ricardo", "(12)934567890", "123456789", UserRole.USER);

    }
}
