package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.unitResidential.UnitResidential;

import java.util.List;

public class UnitResidentialController {

    public void createUnitResidential(String number, String size, String ownerName, String ownerContact) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UnitResidential unitResidential = new UnitResidential();
            unitResidential.setUnitResidentialNumber(number);
            unitResidential.setUnitResidentialSize(size);
            unitResidential.setOwnerName(ownerName);
            unitResidential.setOwnerContact(ownerContact);

            session.save(unitResidential);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public UnitResidential getUnitById(int unitId) {
        Session session = HibernateUtil.getSession();
        UnitResidential unitResidential = null;
        try {
            unitResidential = session.get(UnitResidential.class, unitId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return unitResidential;
    }

    public List<UnitResidential> getAllUnits() {
        Session session = HibernateUtil.getSession();
        List<UnitResidential> units = null;
        try {
            units = session.createQuery("from UnitResidential", UnitResidential.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return units;
    }

    public void updateUnit(int unitId, String newSize, String newNumber, String onewOwnerName, String newOwnerContact) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UnitResidential unitResidential = session.get(UnitResidential.class, unitId);
            if (unitResidential != null) {
                unitResidential.setUnitResidentialNumber(newNumber);
                unitResidential.setUnitResidentialSize(newSize);
                unitResidential.setOwnerName(newOwnerContact);
                unitResidential.setOwnerContact(newOwnerContact);

                session.update(unitResidential);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteUnit(int unitId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UnitResidential unitResidential = session.get(UnitResidential.class, unitId);
            if (unitResidential != null) {
                session.delete(unitResidential);
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
