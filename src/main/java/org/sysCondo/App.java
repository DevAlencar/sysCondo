package org.sysCondo;

import org.hibernate.Session;
import org.sysCondo.controller.*;
import org.sysCondo.infra.DevUtil;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.cost.Cost;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.user.User;
import org.sysCondo.model.user.UserRole;
import org.sysCondo.model.commonArea.CommonArea;

import java.time.LocalDateTime;
import java.util.List;
public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        UserController userController = new UserController();

        DevUtil.clearAllData();
    }
}
