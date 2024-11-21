package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.commonArea.CommonArea;

import java.util.List;

public class CommonAreaController {

    public void createCommonArea(String commonAreaName) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            CommonArea commonArea = new CommonArea();
            commonArea.setCommonAreaName(commonAreaName);

            session.save(commonArea);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public CommonArea getCommonAreaById(Long commonAreaId) {
        Session session = HibernateUtil.getSession();
        CommonArea commonArea = null;
        try {
            commonArea = session.get(CommonArea.class, commonAreaId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return commonArea;
    }

    public List<CommonArea> getAllCommonAreas() {
        Session session = HibernateUtil.getSession();
        List<CommonArea> commonAreas = null;
        try {
            commonAreas = session.createQuery("from CommonArea", CommonArea.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return commonAreas;
    }

    public void updateCommonArea(Long commonAreaId, String newCommonAreaName) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            CommonArea commonArea = session.get(CommonArea.class, commonAreaId);
            if (commonArea != null) {
                commonArea.setCommonAreaName(newCommonAreaName);
                session.update(commonArea);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteCommonArea(Long commonAreaId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            CommonArea commonArea = session.get(CommonArea.class, commonAreaId);
            if (commonArea != null) {
                session.delete(commonArea);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void createCommonAreaIfNotExists(String areaName, Long areaCode) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Long count = session.createQuery("""
            SELECT COUNT(c)
            FROM CommonArea c
            WHERE c.commonAreaName = :areaName
        """, Long.class)
                    .setParameter("areaName", areaName)
                    .uniqueResult();
            if(count == 0){
                CommonArea commonArea = new CommonArea();
                commonArea.setCommonAreaId(areaCode);
                commonArea.setCommonAreaName(areaName);
                session.save(commonArea);
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
