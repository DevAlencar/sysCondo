package org.sysCondo.infra;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sysCondo.model.Statement;
import org.sysCondo.model.account.Account;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.cost.Cost;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.tax.Tax;
import org.sysCondo.model.unitResidential.UnitResidential;
import org.sysCondo.model.user.User;
import org.sysCondo.model.userResidential.UserResidential;
import org.sysCondo.model.userTaxPayed.UserTaxPayed;
import org.sysCondo.model.vehicle.Vehicle;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Dotenv dotenv = Dotenv.load();
            String dbUrl = dotenv.get("DB_NAME");
            String dbUsername = dotenv.get("DB_USER");
            String dbPassword = dotenv.get("DB_PASS");

            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + dotenv.get("DB_NAME") + "?allowPublicKeyRetrieval=true");
            configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USER"));
            configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASS"));
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("show_sql", "true");

            // mapeações aqui
            configuration.addAnnotatedClass(UnitResidential.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Statement.class);
            configuration.addAnnotatedClass(UserResidential.class);
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(Tax.class);
            configuration.addAnnotatedClass(Cost.class);
            configuration.addAnnotatedClass(Maintenance.class);
            configuration.addAnnotatedClass(CommonArea.class);
            configuration.addAnnotatedClass(Booking.class);
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(UserTaxPayed.class);

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
