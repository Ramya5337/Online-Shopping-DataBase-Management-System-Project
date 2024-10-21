package OnlineShopping.dao;

import OnlineShopping.entity.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDaoIMPL implements UserDao {
    
    private static SessionFactory sessionFactory;

    public UserDaoIMPL() {
    	 this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        User user = null;
        try {
            Query query = session.createQuery("FROM User WHERE username = :username");
            query.setParameter("username", username);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user; // Returns the User object or null if not found
    }

    @Override
    public void save(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
