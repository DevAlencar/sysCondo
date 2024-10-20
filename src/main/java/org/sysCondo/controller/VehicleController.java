package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.user.User;
import org.sysCondo.model.vehicle.Vehicle;
import org.sysCondo.model.vehicle.BrandEnum;

import java.util.List;

public class VehicleController {

    public void createVehicle(String number, BrandEnum brand, User user) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(number);
            vehicle.setVehicleBrand(brand);
            vehicle.setUserVehicleFk(user);

            session.save(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Vehicle getVehicleById(Long vehicleId) {
        Session session = HibernateUtil.getSession();
        Vehicle vehicle = null;
        try {
            vehicle = session.get(Vehicle.class, vehicleId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return vehicle;
    }

    public List<Vehicle> getAllVehicles() {
        Session session = HibernateUtil.getSession();
        List<Vehicle> vehicles = null;
        try {
            vehicles = session.createQuery("from Vehicle", Vehicle.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return vehicles;
    }

    public void updateVehicle(Long vehicleId, String newNumber, BrandEnum newBrand, User newUser) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Vehicle vehicle = session.get(Vehicle.class, vehicleId);
            if (vehicle != null) {
                vehicle.setVehicleNumber(newNumber);
                vehicle.setVehicleBrand(newBrand);
                vehicle.setUserVehicleFk(newUser);
                session.update(vehicle);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteVehicle(Long vehicleId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Vehicle vehicle = session.get(Vehicle.class, vehicleId);
            if (vehicle != null) {
                session.delete(vehicle);
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
