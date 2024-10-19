package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.user.User;
import org.sysCondo.model.user.UserRole;

import java.util.List;

public class UserController {

    public void createUser(String name, String contact, String document, UserRole role) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User user = new User();
            user.setName(name);
            user.setContact(contact);
            user.setDocument(document);
            user.setRole(role);

            session.save(user);
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
                user.setName(newName);
                user.setContact(newContact);
                user.setDocument(newDocument);
                user.setRole(newRole);
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
