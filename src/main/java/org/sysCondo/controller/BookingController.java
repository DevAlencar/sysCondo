package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.user.User;

import java.time.LocalDate;
import java.util.List;

public class BookingController {

    public void createBooking(User user, CommonArea commonArea, LocalDate bookingDate) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Booking booking = new Booking();
            booking.setUserBookingFk(user);
            booking.setCommonAreaBookingFk(commonArea);
            booking.setBookingDate(bookingDate);

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

    public void updateBooking(Long bookingId, User newUser, CommonArea newCommonArea, LocalDate newBookingDate) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Booking booking = session.get(Booking.class, bookingId);
            if (booking != null) {
                booking.setUserBookingFk(newUser);
                booking.setCommonAreaBookingFk(newCommonArea);
                booking.setBookingDate(newBookingDate);
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
