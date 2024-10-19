package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.ownerResidential.OwnerResidential;
import org.sysCondo.model.unitResidential.UnitResidential;

import java.util.List;

public class UnitResidentialController {

    public void createUnitResidential(float size, OwnerResidential ownerResidential) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UnitResidential unitResidential = new UnitResidential();
            unitResidential.setSize(size);
            unitResidential.setOwnerResidential(ownerResidential);

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

    public void updateUnit(int unitId, float newSize, OwnerResidential newOwnerResidential) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UnitResidential unitResidential = session.get(UnitResidential.class, unitId);
            if (unitResidential != null) {
                unitResidential.setSize(newSize);
                unitResidential.setOwnerResidential(newOwnerResidential);
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
