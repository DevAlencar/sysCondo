package org.sysCondo.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class MaintenanceController {

    public Long createMaintenance(User userMaintenance, CommonArea commonAreaMaintenance, String status, String type) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Maintenance maintenance = new Maintenance();
            maintenance.setUserMaintenanceFk(userMaintenance);
            maintenance.setCommonAreaMaintenanceFk(commonAreaMaintenance);
            maintenance.setStatus(status);
            maintenance.setType(type);
            // maintenance.setCreatedAt com a data e horario formatada
            // data e hora formata
            maintenance.setCreatedAt(LocalDateTime.now());

            session.save(maintenance);
            transaction.commit();

            return maintenance.getMaintenanceId();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
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

    // retorna todas as manutenções
    public List<Maintenance> getAllMaintenances() {
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

    // retorna as manutenções feitas por um usuário em específico
    public List<Maintenance> getMaintenancesByUserId(Long userId) {
        Session session = HibernateUtil.getSession();
        List<Maintenance> maintenances = null;
        try {
            maintenances = session.createQuery("from Maintenance where userMaintenanceFk.userId = :userId", Maintenance.class)
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return maintenances;
    }

    // retorna as manutencoes feitas por um usuario pelo documento
    public List<Maintenance> getMaintenancesByUserDocument(String userDocument) {
        Session session = HibernateUtil.getSession();
        List<Maintenance> maintenances = null;
        try {
            maintenances = session.createQuery("from Maintenance where userMaintenanceFk.userDocument = :userDocument", Maintenance.class)
                    .setParameter("userDocument", userDocument)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return maintenances;
    }

}
