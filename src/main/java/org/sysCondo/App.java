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
import org.sysCondo.views.LoginScreen;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        UserController userController = new UserController();

        DevUtil.clearAllData();

        // cria um usuário
        User user = userController.createUser("João", "11999999999", "123456789", UserRole.USER, "101", null);

        // cria a aplicação, iniciando com a tela de login
        JFrame app = new JFrame();
        LoginScreen loginScreen = new LoginScreen(app);
        app.setTitle("SysCondo");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setMinimumSize(new Dimension(1200, 800));
        app.setLocationRelativeTo(null); // Centraliza a janela
        app.setExtendedState(JFrame.MAXIMIZED_BOTH);
        app.add(loginScreen);

        app.setVisible(true);

    }
}
