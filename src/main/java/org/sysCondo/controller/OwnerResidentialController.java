package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.unitResidential.UnitResidential;
import org.sysCondo.model.ownerResidential.OwnerResidential;

import java.time.LocalDate;
import java.util.List;

public class OwnerResidentialController {

    public void createOwnerResidential(String ownerName, String ownerDocument, String ownerContact, List<UnitResidential> unitsResidentials) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            OwnerResidential ownerResidential = new OwnerResidential();
            ownerResidential.setOwnerName(ownerName);
            ownerResidential.setOwnerDocument(ownerDocument);
            ownerResidential.setOwnerContact(ownerContact);
            ownerResidential.setUnitsResidentials(unitsResidentials);

            session.save(ownerResidential);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public OwnerResidential getOwnerResidentialById(int ownerResidentialId) {
        Session session = HibernateUtil.getSession();
        OwnerResidential ownerResidential = null;
        try {
            ownerResidential = session.get(OwnerResidential.class, ownerResidentialId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ownerResidential;
    }

    public List<OwnerResidential> getAllOwnerResidential() {
        Session session = HibernateUtil.getSession();
        List<OwnerResidential> ownerResidentials = null;
        try {
            ownerResidentials = session.createQuery("from OwnerResidential", OwnerResidential.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ownerResidentials;
    }

    public void updateOwnerResidential(int ownerResidentialId, String ownerName, String ownerDocument, String ownerContact, List<UnitResidential> unitsResidentials) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            OwnerResidential ownerResidential = session.get(OwnerResidential.class, ownerResidentialId);
            if (ownerResidential != null) {
                ownerResidential.setOwnerName(ownerName);
                ownerResidential.setOwnerDocument(ownerDocument);
                ownerResidential.setOwnerContact(ownerContact);
                ownerResidential.setUnitsResidentials(unitsResidentials);
                session.update(ownerResidential);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteOwnerResidential(int ownerResidentialId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            OwnerResidential ownerResidential = session.get(OwnerResidential.class, ownerResidentialId);
            if (ownerResidential != null) {
                session.delete(ownerResidential);
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
