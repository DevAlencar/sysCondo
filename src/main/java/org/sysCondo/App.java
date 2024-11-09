package org.sysCondo;

import org.hibernate.Session;
import org.sysCondo.controller.*;
import org.sysCondo.infra.DevUtil;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.booking.Booking;
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

        // Cria um usuário normal
        userController.createUser("Ricardo", "(12)934567890", "123", UserRole.USER);
        // Cria um usuário admin
        userController.createUser("admin", "(12)934567890", "456", UserRole.ADMIN);
        
        // Cria algumas áreas comuns
        CommonAreaController commonAreaController = new CommonAreaController();
        commonAreaController.createCommonArea("Salão de festas");
        commonAreaController.createCommonArea("Churrasqueira");
        commonAreaController.createCommonArea("Piscina");

        // Lista todas as áreas comuns
        List<CommonArea> commonAreas = commonAreaController.getAllCommonAreas();
        CommonArea common = commonAreas.get(1);

        // Cria algumas reservas
        BookingController bookingController = new BookingController();
        bookingController.createBooking(userController.getUserByDocument("123"), common, LocalDateTime.now(), 2);
        bookingController.createBooking(userController.getUserByDocument("123"), common, LocalDateTime.now().plusDays(1), 3);
        bookingController.createBooking(userController.getUserByDocument("456"), common, LocalDateTime.now().plusDays(1).plusHours(3).plusMinutes(1), 4);

        // Lista as reservas do espaço selecionado
        List<Booking> bookings = bookingController.getBookingsByCommonAreaId(common.getCommonAreaId());

        // Apresenta as reservas com informações detalhadas
        System.out.println("\n=== Reservas para a área comum: " + common.getCommonAreaName() + " ===");
        for (Booking booking : bookings) {
            String areaName = booking.getCommonAreaBookingFk().getCommonAreaName();
            String userName = booking.getUserBookingFk().getUserName();
            LocalDateTime bookingDate = booking.getBookingDateTime();

            System.out.println("Usuário: " + userName + " | Área: " + areaName + " | Data: " + bookingDate + " | Duração: " + booking.getBookingDuration() + " horas");
        }
    }
}
