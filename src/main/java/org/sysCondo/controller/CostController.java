package org.sysCondo.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.cost.Cost;
import org.sysCondo.model.maintenance.Maintenance;


import java.util.List;

public class CostController {

    public void createCost(Maintenance maintenance, float value, String description) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Cost cost = new Cost();
            cost.setMaintenance(maintenance);
            cost.setValue(value);
            cost.setDescription(description);
            session.save(cost);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Cost getCostById(Long costId) {
        Session session = HibernateUtil.getSession();
        Cost cost = null;
        try {
            cost = session.get(Cost.class, costId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cost;
    }

    public List<Cost> getAllCostsByMaintenance(Maintenance maintenance) {
        Session session = HibernateUtil.getSession();
        List<Cost> costs = null;
        try {
            costs = session.createQuery("from Cost c where c.maintenance = :maintenance", Cost.class)
                    .setParameter("maintenance", maintenance)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return costs;
    }

    public void updateCost(Long costId, Maintenance newMaintenance, float newValue, String newDescription) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Cost cost = session.get(Cost.class, costId);
            if (cost != null) {
                cost.setMaintenance(newMaintenance);
                cost.setValue(newValue);
                cost.setDescription(newDescription);
                session.update(cost);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteCost(Long costId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Cost cost = session.get(Cost.class, costId);
            if (cost != null) {
                session.delete(cost);
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
