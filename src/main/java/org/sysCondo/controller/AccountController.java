package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.account.Account;

import java.time.LocalDate;
import java.util.List;

public class AccountController {

    public void createAccount(String supplier, float value, String status, LocalDate finishDate) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Account account = new Account();
            account.setValue(value);
            account.setStatus(status);
            account.setFinishDate(finishDate);
            account.setSupplier(supplier);

            session.save(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Account getAccountById(int accountId) {
        Session session = HibernateUtil.getSession();
        Account account = null;
        try {
            account = session.get(Account.class, accountId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return account;
    }

    public List<Account> getAllAccounts() {
        Session session = HibernateUtil.getSession();
        List<Account> accounts = null;
        try {
            accounts = session.createQuery("from Account", Account.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return accounts;
    }

    public void updateAccount(int accountId, String supplier, float value, String status, LocalDate finishDate) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                account.setValue(value);
                account.setStatus(status);
                account.setFinishDate(finishDate);
                account.setSupplier(supplier);
                session.update(account);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAccount(int accountId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                session.delete(account);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateAccountStatus(int accountId, String status) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                account.setStatus(status);
                session.update(account);
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
