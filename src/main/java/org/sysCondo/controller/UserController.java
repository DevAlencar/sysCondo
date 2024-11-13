package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.user.User;
import org.sysCondo.model.user.UserRole;
import org.sysCondo.model.vehicle.Vehicle;

import java.util.List;

public class UserController {

    public void createUser(String name, String contact, String document, UserRole role, List<Vehicle> vehicles) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Criar e salvar o usuário primeiro
            User user = new User();
            user.setUserName(name);
            user.setUserContact(contact);
            user.setUserDocument(document);
            user.setUserRole(role);

            session.save(user); // Salva o usuário primeiro para gerar um ID
            session.flush(); // Garante que o ID seja gerado imediatamente

            // Agora salva os veículos associados ao usuário
            for (Vehicle vehicle : vehicles) {
                vehicle.setUserVehicleFk(user); // Associa o veículo ao usuário
                session.save(vehicle); // Salva cada veículo
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public User getUserById(Long userId) {
        Session session = HibernateUtil.getSession();
        User user = null;
        try {
            user = session.get(User.class, userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }


    public User getUserByDocument(String document) {
        Session session = HibernateUtil.getSession();
        User user = null;
        try {
            user = session.createQuery("from User where userDocument = :document", User.class)
                    .setParameter("document", document)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSession();
        List<User> users = null;
        try {
            users = session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public void updateUser(Long userId, String newName, String newContact, String newDocument, UserRole newRole) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setUserName(newName);
                user.setUserContact(newContact);
                user.setUserDocument(newDocument);
                user.setUserRole(newRole);
                session.update(user);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteUser(Long userId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
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
