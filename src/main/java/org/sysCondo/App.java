package org.sysCondo;

import org.hibernate.Session;
import org.sysCondo.infra.HibernateUtil;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
    }
}
