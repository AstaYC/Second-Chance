package com.secondChance.DAO.DAOImpl;

import com.secondChance.DAO.Interface.UserInterface;
import com.secondChance.Models.UserEntity;
import com.secondChance.Util.HibernateUtil;
import com.secondChance.Util.PasswordUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserInterface {

    @Override
    public void createUser(UserEntity user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public UserEntity getUserById(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(UserEntity.class, id);
        }
    }


    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity user = null;
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM UserEntity u WHERE u.email = :email";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            query.setParameter("email", email);
            user = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return user;
    }


    @Override
    public List<UserEntity> getAllUsers() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from User", UserEntity.class).list();
        }
    }

    @Override
    public void updateUser(UserEntity user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            UserEntity user = session.get(UserEntity.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean validateUser(String email, String inputPassword) {
        boolean flag = false;
        UserEntity user = getUserByEmail(email); // Fetch user by email

        if (user == null) {
            flag = false; // No user found with the provided email
        } else {
            // Use a utility method to verify the hashed password
            if (PasswordUtil.verifyPassword(inputPassword, user.getPassword())) {
                flag = true; // Password matches
            }
        }
        return flag;
    }

}
