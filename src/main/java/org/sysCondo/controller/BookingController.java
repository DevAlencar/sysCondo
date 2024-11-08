package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingController {

    public void createBooking(User user, CommonArea commonArea, LocalDateTime bookingDate, Integer bookingDuration) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<Booking> bookings = getBookingsByCommonAreaId(commonArea.getCommonAreaId());

        for (Booking booking : bookings) {
            LocalDateTime bookingStart = booking.getBookingDateTime();
            LocalDateTime bookingEnd = bookingStart.plusHours(booking.getBookingDuration());
            LocalDateTime newBookingEnd = bookingDate.plusHours(bookingDuration);

            // Verifica se há um conflito de horário
            boolean isConflicting =
                    (bookingDate.isBefore(bookingEnd) && newBookingEnd.isAfter(bookingStart));

            if (isConflicting) {
                System.out.println("Conflito de horário! A reserva não pode ser realizada.");
                return;
            }
        }

        try {
            Booking booking = new Booking();
            booking.setUserBookingFk(user);
            booking.setCommonAreaBookingFk(commonArea);
            booking.setBookingDateTime(bookingDate);
            booking.setBookingDuration(bookingDuration);

            session.save(booking);
            transaction.commit();
            // sout
            System.out.println("Booking created successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    // retorna todas as reservas de uma área comum (para verificar disponibilidade)
    public List<Booking> getBookingsByCommonAreaId(Long commonAreaId) {
        Session session = HibernateUtil.getSession();
        List<Booking> bookings = null;
        try {
            bookings = session.createQuery("from Booking where commonAreaBookingFk.commonAreaId = :commonAreaId", Booking.class)
                    .setParameter("commonAreaId", commonAreaId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bookings;
    }

    public Booking getBookingById(Long bookingId) {
        Session session = HibernateUtil.getSession();
        Booking booking = null;
        try {
            booking = session.get(Booking.class, bookingId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return booking;
    }

    // lista as reservas atuais
    public List<Booking> getAllBookings() {
        Session session = HibernateUtil.getSession();
        List<Booking> bookings = null;
        try {
            bookings = session.createQuery("from Booking", Booking.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bookings;
    }

    public void updateBooking(Long bookingId, User newUser, CommonArea newCommonArea, LocalDateTime newBookingDate, Integer newBookingDuration) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Booking booking = session.get(Booking.class, bookingId);
            if (booking != null) {
                booking.setUserBookingFk(newUser);
                booking.setCommonAreaBookingFk(newCommonArea);
                booking.setBookingDateTime(newBookingDate);
                booking.setBookingDuration(newBookingDuration);
                session.update(booking);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteBooking(Long bookingId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Booking booking = session.get(Booking.class, bookingId);
            if (booking != null) {
                session.delete(booking);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
