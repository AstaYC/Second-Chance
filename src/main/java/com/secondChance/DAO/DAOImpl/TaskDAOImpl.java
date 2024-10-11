package com.secondChance.DAO.DAOImpl;

import com.secondChance.DAO.Interface.TaskInterface;
import com.secondChance.Models.TaskEntity;
import com.secondChance.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDAOImpl implements TaskInterface {

    @Override
    public void createTask(TaskEntity task) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(TaskEntity.class, id);
        }
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from TaskEntity", TaskEntity.class).list();
        }
    }

    @Override
    public void updateTask(TaskEntity task) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            TaskEntity task = session.get(TaskEntity.class, id);
            if (task != null) {
                session.delete(task);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
