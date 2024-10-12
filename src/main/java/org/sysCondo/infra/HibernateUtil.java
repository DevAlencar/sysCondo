package org.sysCondo.infra;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sysCondo.models.ownerResidential.OwnerResidential;
import org.sysCondo.models.unitResidential.UnitResidential;
import org.sysCondo.models.user.User;
import org.sysCondo.models.userResidential.UserResidential;
import org.sysCondo.models.vehicle.Vehicle;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Dotenv dotenv = Dotenv.load();

            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + dotenv.get("DB_NAME") + "?allowPublicKeyRetrieval=true");
            configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USER"));
            configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("show_sql", "true");

            // mapeações aqui
            configuration.addAnnotatedClass(OwnerResidential.class);
            configuration.addAnnotatedClass(UnitResidential.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(UserResidential.class);
            configuration.addAnnotatedClass(Vehicle.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        sessionFactory.close();
    }

}
