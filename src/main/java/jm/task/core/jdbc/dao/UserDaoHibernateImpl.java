package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private static Transaction transaction = null;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, name VARCHAR(45), " +
                "lastName VARCHAR(45), age TINYINT, PRIMARY KEY (id))";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            users = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
}
