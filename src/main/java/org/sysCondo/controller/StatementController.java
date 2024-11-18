package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.Statement;
// list import
import java.util.List;

import java.time.LocalDate;

public class StatementController {

    public void createStatement(String title, String description) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Statement statement = new Statement();
            statement.setStatementTitle(title);
            statement.setStatementDescription(description);
            statement.setStatementDate(LocalDate.now());
            statement.setStatementStatus("Ativo");

            session.save(statement);
            transaction.commit();
            System.out.println("Anúncio criado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Statement> getAllStatements() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Statement> statements = session.createQuery("from Statement ").list();
            return statements;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
