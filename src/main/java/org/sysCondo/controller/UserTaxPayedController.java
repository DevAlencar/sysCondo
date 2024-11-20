package org.sysCondo.controller;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.tax.Tax;
import org.sysCondo.model.user.User;
import org.sysCondo.model.userTaxPayed.UserTaxPayed;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserTaxPayedController {
    public void createUserTaxPayed(User user, Tax tax) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UserTaxPayed userTaxPayed = new UserTaxPayed();
            userTaxPayed.setFkUser(user);
            userTaxPayed.setFkTax(tax);

            session.save(userTaxPayed);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public UserTaxPayed getUserTaxPayedById(Long userTaxPayedId) {
        Session session = HibernateUtil.getSession();
        UserTaxPayed userTaxPayed = null;
        try {
            userTaxPayed = session.get(UserTaxPayed.class, userTaxPayedId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userTaxPayed;
    }

    public List<UserTaxPayed> getAllUserTaxPayed() {
        Session session = HibernateUtil.getSession();
        List<UserTaxPayed> userTaxPayeds = null;
        try {
            userTaxPayeds = session.createQuery("from UserTaxPayed", UserTaxPayed.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userTaxPayeds;
    }

    public void deleteUserTaxPayeds(Long userTaxPayedId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UserTaxPayed userTaxPayed = session.get(UserTaxPayed.class, userTaxPayedId);
            if (userTaxPayed != null) {
                session.delete(userTaxPayed);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public String obterStatusTaxa(Long usuarioId, int taxaId) {
        Session session = HibernateUtil.getSession();
        String status = "A Pagar";


        try {
            // A consulta HQL agora assume que você tem a relação correta nas entidades
            Long count = session.createQuery("""
            SELECT COUNT(p)
            FROM UserTaxPayed p
            WHERE p.fkUser.userId = :usuarioId AND p.fkTax.id = :taxaId
        """, Long.class)
                    .setParameter("usuarioId", usuarioId)
                    .setParameter("taxaId", taxaId)
                    .uniqueResult();
            if (count != null && count > 0) {
                status = "Paga";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return status;
    }

}
