package org.sysCondo.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.user.User;


import java.util.List;

public class MaintenanceController {

    public void createMaintenance(User userMaintenance, CommonArea commonAreaMaintenance, String status) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Maintenance maintenance = new Maintenance();
            maintenance.setUserMaintenanceFk(userMaintenance);
            maintenance.setCommonAreaMaintenanceFk(commonAreaMaintenance);
            maintenance.setStatus(status);
            session.save(maintenance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Maintenance getMaintenanceById(Long maintenanceId) {
        Session session = HibernateUtil.getSession();
        Maintenance maintenance = null;
        try {
            maintenance = session.get(Maintenance.class, maintenanceId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return maintenance;
    }

    public List<Maintenance> getAllMaintenances(Long maintenanceFk) {
        Session session = HibernateUtil.getSession();
        List<Maintenance> maintenances = null;
        try {
            maintenances = session.createQuery("from Maintenance", Maintenance.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return maintenances;
    }

    public void updateMaintenance(Long maintenanceId, User newUserMaintenance, CommonArea newCommonAreaMaintenance, String newStatus) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Maintenance maintenance = session.get(Maintenance.class, maintenanceId);
            if (maintenance != null) {
                maintenance.setUserMaintenanceFk(newUserMaintenance);
                maintenance.setCommonAreaMaintenanceFk(newCommonAreaMaintenance);
                maintenance.setStatus(newStatus);
                session.update(maintenance);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteMaintenance(Long maintenanceId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Maintenance maintenance = session.get(Maintenance.class, maintenanceId);
            if (maintenance != null) {
                session.delete(maintenance);
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
