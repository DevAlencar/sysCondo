package org.sysCondo.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sysCondo.infra.HibernateUtil;
import org.sysCondo.model.user.User;
import org.sysCondo.model.userMessage.UserMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserMessageController {

    public void createMessage(User userFrom, String title, String messageContent) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UserMessage userMessage = new UserMessage();
            userMessage.setUserFrom(userFrom);
            userMessage.setTitle(title);
            userMessage.setMessage(messageContent);
            userMessage.setCreatedAt(LocalDate.now());
            session.save(userMessage);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<UserMessage> getAllMessages() {
        Session session = HibernateUtil.getSession();
        List<UserMessage> messages = null;

        try {
            messages = session.createQuery("from UserMessage", UserMessage.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return messages;
    }

    public UserMessage getMessageById(Long messageId) {
        Session session = HibernateUtil.getSession();
        UserMessage userMessage = null;

        try {
            userMessage = session.get(UserMessage.class, messageId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return userMessage;
    }

    public List<UserMessage> getMessagesByUser(User userFrom) {
        Session session = HibernateUtil.getSession();
        List<UserMessage> messages = null;

        try {
            messages = session.createQuery("from UserMessage um where um.userFrom = :userFrom", UserMessage.class)
                    .setParameter("userFrom", userFrom)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return messages;
    }

    public void updateMessage(Long messageId, String newTitle, String newMessageContent) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UserMessage userMessage = session.get(UserMessage.class, messageId);
            if (userMessage != null) {
                userMessage.setTitle(newTitle);
                userMessage.setMessage(newMessageContent);
                session.update(userMessage);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteMessage(Long messageId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            UserMessage userMessage = session.get(UserMessage.class, messageId);
            if (userMessage != null) {
                session.delete(userMessage);
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
