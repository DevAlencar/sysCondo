package org.sysCondo.infra;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DevUtil {

    // Método para apagar todos os dados do banco de dados
    public static void clearAllData() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Desabilitar restrições de chave estrangeira (MySQL)
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

            // Apagar dados das tabelas (em ordem reversa para evitar problemas de FK)
            session.createQuery("DELETE FROM Booking").executeUpdate();
            session.createQuery("DELETE FROM Vehicle").executeUpdate();
            session.createQuery("DELETE FROM UserResidential").executeUpdate();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.createQuery("DELETE FROM UnitResidential").executeUpdate();
            session.createQuery("DELETE FROM Tax").executeUpdate();
            session.createQuery("DELETE FROM Maintenance").executeUpdate();
            session.createQuery("DELETE FROM Cost").executeUpdate();
            session.createQuery("DELETE FROM CommonArea").executeUpdate();

            // Reativar restrições de chave estrangeira
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

            transaction.commit();
            System.out.println("Todos os dados foram apagados com sucesso.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para popular o banco de dados com dados de teste
    public static void populateTestData() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Exemplo de inserção de dados de teste
            // Adicione seus métodos de criação de entidades aqui
            // User user = new User("admin", "admin@example.com", "password");
            // session.save(user);

            transaction.commit();
            System.out.println("Dados de teste foram inseridos com sucesso.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para resetar os dados de uma tabela específica
    public static void clearTable(String tableName) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM " + tableName).executeUpdate();
            transaction.commit();
            System.out.println("Tabela " + tableName + " foi limpa com sucesso.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para exibir o número de registros em uma tabela
    public static void countRecords(String entityName) {
        Session session = HibernateUtil.getSession();
        try {
            Long count = session.createQuery("SELECT COUNT(e) FROM " + entityName + " e", Long.class)
                    .getSingleResult();
            System.out.println("Total de registros na tabela " + entityName + ": " + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso dos métodos
        clearAllData();
        populateTestData();
        countRecords("User");
    }
}
