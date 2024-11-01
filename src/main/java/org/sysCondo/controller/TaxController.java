package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.tax.Tax;
import org.sysCondo.model.user.User;

import java.time.LocalDate;
import java.util.List;

public class TaxController {

    public void createTax(User user, float value, String status, LocalDate finishDate) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Tax tax = new Tax();
            tax.setUserTaxFk(user);
            tax.setValue(value);
            tax.setStatus(status);
            tax.setFinishDate(finishDate);

            session.save(tax);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Tax getTaxById(int taxId) {
        Session session = HibernateUtil.getSession();
        Tax tax = null;
        try {
            tax = session.get(Tax.class, taxId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tax;
    }

    public List<Tax> getAllTaxes() {
        Session session = HibernateUtil.getSession();
        List<Tax> taxes = null;
        try {
            taxes = session.createQuery("from Tax", Tax.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return taxes;
    }

    public void updateTax(int taxId, User user, float value, String status, LocalDate finishDate) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Tax tax = session.get(Tax.class, taxId);
            if (tax != null) {
                tax.setUserTaxFk(user);
                tax.setValue(value);
                tax.setStatus(status);
                tax.setFinishDate(finishDate);
                session.update(tax);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteTax(int taxId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Tax tax = session.get(Tax.class, taxId);
            if (tax != null) {
                session.delete(tax);
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
